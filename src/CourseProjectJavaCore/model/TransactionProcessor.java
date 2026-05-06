/**
 * Classname    TransactionProcessor
 * @version     0.06
 * @author      Aleksei Borzetsov
 * date         06.05.2026
 */

package CourseProjectJavaCore.model;

import CourseProjectJavaCore.exceptions.*;

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

    private final ArrayList<TransactionReport> report = new ArrayList<>();

    private Path reportFilePath = Path.of(TransactionReport.DEFAULT_PATH);

    public static TransactionProcessor INSTANCE = new TransactionProcessor();

    private TransactionProcessor() {
        //Проверка существования базы данных
        Path accountFilePath = Path.of(Account.DEFAULT_PATH);
        Path accountFile = accountFilePath.resolve(Account.FILE_NAME + Account.FILE_EXTENSION);
        try {
            Account.dataBaseConnect(accountFile);
        } catch (IOException e) {
            report.add(new TransactionReport(
                    ZonedDateTime.now(ZoneId.of("UTC")),
                    "",
                    e.getMessage(),
                    ""));
        }
    }

    public static TransactionProcessor getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Выполняет все платежные поручения
     * @throws FatalErrorException -- ошибка работы программы
     */
    public void executePaymentOrders() throws FatalErrorException {
        //Получить список платежных поручений
        ArrayList<Path> paymentOrderFiles;
        try (Stream<Path> allFiles = Files.list(Path.of(PaymentOrder.DEFAULT_PATH))) {
            paymentOrderFiles = allFiles
                    //Обычный файл (не ярлык и не папка)
                    .filter(p -> Files.isRegularFile(p, LinkOption.NOFOLLOW_LINKS))
                    //Имеет соответствующее расширение
                    .filter(p -> p.toString().endsWith(PaymentOrder.FILE_EXTENSION))
                    //Добавить в список
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new FatalErrorException("чтение платежных поручений");
        }
        for (Path currentPaymentOrderFile : paymentOrderFiles) {
            report.add(executePaymentOrder(currentPaymentOrderFile));
            try {
                Files.write(currentPaymentOrderFile,
                        ("\r\n\r\n" + report.get(report.size() - 1).toString()).getBytes(),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new FatalErrorException("отметка банка");
            }
            //Переместить платежное поручение в архив
            Path inputPath = Path.of(PaymentOrder.DEFAULT_PATH);
            Path archivePath = Path.of(PaymentOrder.ARCHIVE_PATH);
            //Создание директории
            if (Files.notExists(inputPath)) {
                try {
                    Files.createDirectories(inputPath);
                } catch (IOException e) {
                    throw new FatalErrorException("создание директории платежных поручений");
                }
            }
            if (Files.notExists(archivePath)) {
                try {
                    Files.createDirectories(archivePath);
                } catch (IOException e) {
                    throw new FatalErrorException("создание директории архива");
                }
            }
            Path inputFile = inputPath.resolve(currentPaymentOrderFile.getFileName());
            Path archiveFile = archivePath.resolve(currentPaymentOrderFile.getFileName());
            try {
                Files.move(inputFile, archiveFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new FatalErrorException("архивирование платежных поручений");
            }
        }
    }

    /**
     * Выполняет платежное поручение из указанного файла
     * @param paymentOrderFile -- файл платежного поручения
     * @return отчет об исполнении
     */
    private TransactionReport executePaymentOrder(Path paymentOrderFile) {
        TransactionReport currentTransactionReport = new TransactionReport(
                ZonedDateTime.now(ZoneId.of("UTC")),
                paymentOrderFile.getFileName().toString(),
                "",
                "");
        //Прочитать платежное поручение
        StringBuilder readPaymentOrderFile = new StringBuilder();
        try {
            readPaymentOrderFile.append(Files.readString(paymentOrderFile));
        } catch (IOException e) {
            currentTransactionReport.setMatter(e.getMessage());
            currentTransactionReport.setResult("Denied");
            return  currentTransactionReport;
        }
        //Получить объект платежного поручения
        Pattern paymentOrderPattern = Pattern.compile(PaymentOrder.REGEXP);
        Matcher paymentOrderMatcher = paymentOrderPattern.matcher(readPaymentOrderFile);
        if (paymentOrderMatcher.find()) {
            PaymentOrder currentPaymentOrder = new PaymentOrder(paymentOrderMatcher.group());
            try {
                //Получить сумму перевода
                long paymentValue = currentPaymentOrder.getPaymentValue();
                //Уточнить отчет по транзакции
                currentTransactionReport.setMatter(currentPaymentOrder.getPayerAccountNumber()
                        + " -> " + currentPaymentOrder.getRecipientAccountNumber()
                        + " : " + paymentValue);
                //Получить счета плательщика и получателя
                Account payerAccount = getAccount(currentPaymentOrder.getPayerAccountNumber());
                Account recipientAccount = getAccount(currentPaymentOrder.getRecipientAccountNumber());
                //Списать со счета плательщика
                payerAccount.expense(paymentValue);
                //Зачислить на счет получателя
                recipientAccount.income(paymentValue);
                //Обновить записи в базе данных
                updateAccount(payerAccount);
                updateAccount(recipientAccount);
                currentTransactionReport.setResult("Executed");
                return currentTransactionReport;
            } catch (AccountNotFoundException
                     | IllegalValueException
                     | InsufficientFundsException
                     | DataBaseConnectionException e) {
                currentTransactionReport.setResult("Denied <" + e.getMessage() + ">");
                return currentTransactionReport;
            }
        }
        else {
            currentTransactionReport.setMatter("The file does not contain payment order");
            currentTransactionReport.setResult("Denied");
            return  currentTransactionReport;
        }
    }

    /**
     * Создает файл отчета
     * @throws FatalErrorException -- ошибка работы программы
     */
    public void generateReport() throws FatalErrorException {
        //Создание директории
        if (Files.notExists(this.reportFilePath)) {
            try {
                Files.createDirectories(this.reportFilePath);
            } catch (IOException e) {
                throw new FatalErrorException("создание директории отчета");
            }
        }
        //Создание файла
        Path reportFile = this.reportFilePath.resolve(TransactionReport.FILE_NAME
                + TransactionReport.FILE_EXTENSION);
        try {
            Files.writeString(reportFile, "", StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            if (report.isEmpty()) {
                Files.writeString(reportFile, "No payment orders were found", StandardOpenOption.APPEND);
            }
            else {
                for (TransactionReport currentTransactionReport : report)
                    Files.writeString(reportFile, currentTransactionReport
                            + "\r\n", StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new FatalErrorException("создание файла отчета");
        }
    }

    public void setReportFilePath(String path) {
        this.reportFilePath = Path.of(path);
    }

    /**
     * Найти счет в базе данных
     * @param accountNumber -- запрашиваемый счет
     * @throws AccountNotFoundException -- счет не обнаружен
     * @throws DataBaseConnectionException -- ошибка базы данных
     * @return запрашиваемый счет
     */
    private Account getAccount(String accountNumber) throws AccountNotFoundException, DataBaseConnectionException {
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
            //Если запрашиваемого счета не обнаружено, вызвать исключение
            if (reqAccount.isEmpty()) throw new AccountNotFoundException("Account not found");
            return reqAccount.get(0);
        } catch (IOException e) {
            throw new DataBaseConnectionException("Accounts Data Base Error");
        }
    }

    /**
     * Обновить информацию о счете в базе данных
     * @param account -- объект с новыми данными
     * @throws AccountNotFoundException -- счет не обнаружен
     * @throws DataBaseConnectionException -- ошибка базы данных
     */
    private void updateAccount(Account account) throws AccountNotFoundException, DataBaseConnectionException {
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
            if (reqAccount.isEmpty()) throw new AccountNotFoundException("Account not found");
            //Обновить счет
            accounts.replaceAll(a -> a.getNumber().equals(account.getNumber()) ? account : a);
            //Обновить базу данных
            try {
                Files.writeString(accountFile, "", StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
                for (Account currentAccount : accounts)
                    Files.writeString(accountFile, currentAccount.toString() + "\r\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new DataBaseConnectionException("Accounts Data Base Error");
            }
        } catch (IOException e) {
            throw new DataBaseConnectionException("Accounts Data Base Error");
        }
    }
}
