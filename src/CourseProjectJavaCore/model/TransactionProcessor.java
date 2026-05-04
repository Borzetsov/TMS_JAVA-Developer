/**
 * Classname    TransactionProcessor
 * @version     0.04
 * @author      Aleksei Borzetsov
 * date         04.05.2026
 */

package CourseProjectJavaCore.model;

import java.io.IOException;
import java.nio.file.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TransactionProcessor {

    private ArrayList<TransactionReport> report = new ArrayList<>();
    private boolean canWork = true;

    public static TransactionProcessor INSTANCE = new TransactionProcessor();

    private TransactionProcessor() {
        //Проверка существования базы данных
        Path accountFilePath = Path.of(Account.DEFAULT_PATH);
        Path accountFile = accountFilePath.resolve(Account.FILE_NAME + Account.FILE_EXTENSION);
        if (!Files.exists(accountFile)) {
            report.add(new TransactionReport(
                    ZonedDateTime.now(ZoneId.of("UTC")),
                "",
                "Accounts database not found",
                ""));
            canWork = false;
        }
    }

    public static TransactionProcessor getINSTANCE() {
        return INSTANCE;
    }

    public void executePaymentOrders() {
        if (!canWork) {
            System.out.println("Critical error: accounts database not found");
            return;
        }
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
            ZonedDateTime timeOfTransaction = ZonedDateTime.now(ZoneId.of("UTC"));
            String transactionStatus = new String("Executed");
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
                //Проверить возможность транзакции:
                //Есть ли счет плательщика?
                if (payerAccount == null) {
                    transactionStatus = new String("Denied <The payer's account not found>");
                }
                //Есть ли счет получателя?
                if (recipientAccount == null) {
                    transactionStatus = new String("Denied <The recipient's account not found>");
                }
                //У плательщика достаточно средств?
                if ((payerAccount != null) && (!payerAccount.isExpensePossible(paymentValue))) {
                    transactionStatus = new String("Denied <Insufficient funds in the payer's account>");
                }
                //Проверить, что счета разные
                if ((payerAccount != null) && (recipientAccount != null) &&
                        (payerAccount.getNumber().equals(recipientAccount.getNumber()))) {
                    transactionStatus = new String("Denied <Same account>");
                }
                if ((payerAccount != null) && (recipientAccount != null)
                        && (transactionStatus.equals("Executed"))){
                    //Списать со счета плательщика
                    payerAccount.expense(paymentValue);
                    //Зачислить на счет получателя
                    recipientAccount.income(paymentValue);
                    //Обновить записи в базе данных
                    updateAccount(payerAccount);
                    updateAccount(recipientAccount);
                }
                //Создать отчет о транзакции
                report.add(new TransactionReport(
                        timeOfTransaction,
                        currentPaymentOrderFile.getFileName().toString(),
                        currentPaymentOrder.getPayerAccountNumber() + " -> "
                                + currentPaymentOrder.getRecipientAccountNumber()
                                + " : " + paymentValue,
                        transactionStatus));
                //Поставить в платежном поручении отметку банка
                try {
                    Files.write(currentPaymentOrderFile,
                            ("\r\n\r\n" + transactionStatus + "\r\n" + timeOfTransaction).getBytes(),
                            StandardOpenOption.APPEND);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                //Переместить платежное поручение в архив
                Path inputPath = Path.of(PaymentOrder.DEFAULT_PATH);
                Path archivePath = Path.of(PaymentOrder.ARCHIVE_PATH);
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

    /**
     * Создает файл отчета по стандартному пути
     */
    public void generateReport() {
        Path reportFilePath = Path.of(TransactionReport.DEFAULT_PATH);
        //Создание директории
        if (Files.notExists(reportFilePath)) {
            try {
                Files.createDirectories(reportFilePath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        //Создание файла
        Path reportFile = reportFilePath.resolve(TransactionReport.FILE_NAME
                + TransactionReport.FILE_EXTENSION);
        try {
            Files.writeString(reportFile, "", StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            if (report.isEmpty()) {
                Files.writeString(reportFile, "No payment orders executed", StandardOpenOption.APPEND);
            }
            else {
                for (TransactionReport currentTransactionReport : report)
                    Files.writeString(reportFile, currentTransactionReport
                            + "\r\n", StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Найти счет в базе данных
     * @param accountNumber -- запрашиваемый счет
     * @return запрашиваемый счет
     */
    private Account getAccount(String accountNumber) {
        //Прочитать базу данных
        Path accountFilePath = Path.of(Account.DEFAULT_PATH);
        Path accountFile = accountFilePath.resolve(Account.FILE_NAME + Account.FILE_EXTENSION);
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

    /**
     * Обновить информацию о счете в базе данных
     * @param account -- объект с новыми данными
     * @return -- статус
     */
    private boolean updateAccount(Account account) {
        //Прочитать базу данных
        Path accountFilePath = Path.of(Account.DEFAULT_PATH);
        Path accountFile = accountFilePath.resolve(Account.FILE_NAME + Account.FILE_EXTENSION);
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
            //Есть ли такой счет?
            ArrayList<Account> reqAccount = accounts.stream()
                    .filter(str -> str.getNumber().equals(account.getNumber()))
                    .collect(Collectors.toCollection(ArrayList<Account> :: new));
            //Если нет -- какая-то непонятная ситуация О_о
            if (reqAccount.isEmpty()) return false;
            //Обновить счет
            accounts.replaceAll(a -> a.getNumber().equals(account.getNumber()) ? account : a);
            //Обновить базу данных
            try {
                Files.writeString(accountFile, "", StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
                for (Account currentAccount : accounts)
                    Files.writeString(accountFile, currentAccount.toString() + "\r\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
