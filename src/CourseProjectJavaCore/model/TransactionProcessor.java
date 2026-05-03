/**
 * Classname    TransactionProcessor
 * @version     0.03
 * @author      Aleksei Borzetsov
 * date         03.05.2026
 */

package CourseProjectJavaCore.model;

import java.io.IOException;
import java.nio.file.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TransactionProcessor {

    private ArrayList<TransactionReport> report = new ArrayList<>();

    public static TransactionProcessor INSTANCE = new TransactionProcessor();

    private TransactionProcessor() {

    }

    public static TransactionProcessor getINSTANCE() {
        return INSTANCE;
    }

    public void executePaymentOrders() {
        //Получить список платежных поручений
        ArrayList<Path> paymentOrderFiles = new ArrayList<>();
        try (Stream<Path> allFiles = Files.list(Path.of(PaymentOrder.DEFAULT_PATH))) {
            paymentOrderFiles = allFiles
                    //Обычный файл (не ярлык и не папка)
                    .filter(p -> Files.isRegularFile(p, LinkOption.NOFOLLOW_LINKS))
                    //Имеет соответствующее расширение
                    .filter(p -> p.toString().endsWith(PaymentOrder.FILE_EXTENSION))
                    //Добавить в список
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (Path currentPaymentOrderFile : paymentOrderFiles) {
            //Прочитать платежное поручение
            StringBuilder readPaymentOrderFile = new StringBuilder();
            try {
                readPaymentOrderFile.append(Files.readString(currentPaymentOrderFile));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            //Получить объект платежного поручения
            Pattern paymentOrderPattern = Pattern.compile(PaymentOrder.REGEXP);
            Matcher paymentOrderMatcher = paymentOrderPattern.matcher(readPaymentOrderFile);
            PaymentOrder currentPaymentOrder;
            if (paymentOrderMatcher.find()) {
                currentPaymentOrder = new PaymentOrder(paymentOrderMatcher.group());
                //Получить счета плательщика и получателя
                Account payerAccount = getAccount(currentPaymentOrder.getPayerAccountNumber());
                Account recipientAccount = getAccount(currentPaymentOrder.getRecipientAccountNumber());
                //Получить сумму перевода
                long paymentValue = currentPaymentOrder.getPaymentValue();
                //Проверить возможность транзакции: счета есть, у плательщика достаточно средств
                if ((payerAccount != null) && (recipientAccount != null)
                        && (payerAccount.isExpensePossible(paymentValue))) {
                    //Списать со счета плательщика
                    payerAccount.expense(paymentValue);
                    //Зачислить на счет получателя
                    recipientAccount.income(paymentValue);
                    //Обновить записи в базе данных
                    updateAccount(payerAccount);
                    updateAccount(recipientAccount);
                    //Создать отчет о транзакции
                    report.add(new TransactionReport(currentPaymentOrderFile.getFileName().toString(),
                            payerAccount.getNumber() + " -> "
                                    + recipientAccount.getNumber() + " : " + paymentValue,
                            "Executed"));
                    //Поставить в платежном поручении отметку банка
                    try {
                        Files.write(currentPaymentOrderFile,
                                ("\r\n\r\nExecuted " + ZonedDateTime.now() + " ").getBytes(),
                                StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    //Переместить платежное поручение в архив
                    Path inputPath = Path.of(PaymentOrder.DEFAULT_PATH);
                    Path archivePath = Path.of(TransactionReport.DEFAULT_PATH);
                    //Создание директории
                    if (Files.notExists(inputPath)) {
                        try {
                            Files.createDirectories(inputPath);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (Files.notExists(archivePath)) {
                        try {
                            Files.createDirectories(archivePath);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Path inputFile = inputPath.resolve(currentPaymentOrderFile.getFileName());
                    Path archiveFile = archivePath.resolve(currentPaymentOrderFile.getFileName());
                    try {
                        Files.move(inputFile, archiveFile, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        generateReport();
    }

    public void generateReport() {
        for (TransactionReport currentTransactionReport : report) System.out.println(currentTransactionReport);
    }

    /**
     * Найти счет в базе данных
     * @param accountNumber -- запрашиваемый счет
     * @return запрашиваемый счет
     */
    private Account getAccount(String accountNumber) {
        //Прочитать базу данных
        Path accountFilePath = Path.of(Account.DEFAULT_PATH);
        Path accountFile = accountFilePath.resolve(Account.FILE_NAME + Account.DATABASE_FILE_EXTENSION);
        StringBuilder readAccountFile = new StringBuilder();
        try {
            readAccountFile.append(Files.readString(accountFile));
            //Получить список счетов
            ArrayList<Account> accounts = new ArrayList<>();
            Pattern accountPattern = Pattern.compile(Account.REGEXP);
            Matcher accountMatcher = accountPattern.matcher(readAccountFile);
            while (accountMatcher.find()) {
                accounts.add(new Account(accountMatcher.group()));
            }
            //Получить список счетов с указанным номером (должен быть единственный счет)
            ArrayList<Account> reqAccount = accounts.stream()
                    .filter(str -> str.getNumber().equals(accountNumber))
                    .collect(Collectors.toCollection(ArrayList<Account> :: new));
            //Вернуть null, если счетов нет, иначе первый счет из списка O_o
            return (reqAccount.isEmpty()) ? null : reqAccount.get(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private boolean updateAccount(Account account) {
        //Обновить запись в базе данных
        return true;
    }
}
