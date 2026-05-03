/**
 * Classname    CourceProjectJavaCore
 * @version     0.04
 * @author      Aleksei Borzetsov
 * date         03.05.2026
 */
 
package CourseProjectJavaCore;

import CourseProjectJavaCore.model.Account;
import CourseProjectJavaCore.model.PaymentOrder;
import CourseProjectJavaCore.model.TransactionProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public final class CourseProjectJavaCore {

    public void run() {
        System.out.println("Курсовой проект №1 Java Core");
        System.out.println();

        System.out.println("Создание списка счетов");
        ArrayList<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            accounts.add(new Account());
        }
        for (Account currentAccount : accounts) System.out.println(currentAccount);
        System.out.println();

        System.out.println("Пополнение баланса");
        for (Account currentAccount : accounts) {
            currentAccount.income((long) (Math.random() * 1000000L));
        }
        for (Account currentAccount : accounts) System.out.println(currentAccount);
        System.out.println();

        System.out.println("Запись в файл");
        Path accountFilePath = Path.of(Account.DEFAULT_PATH);
        //Создание директории
        if (Files.notExists(accountFilePath)) {
            try {
                Files.createDirectories(accountFilePath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        //Создание файла
        Path accountFile = accountFilePath.resolve(Account.FILE_NAME + Account.DATABASE_FILE_EXTENSION);
        try {
            Files.writeString(accountFile, "", StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            for (Account currentAccount : accounts)
                Files.writeString(accountFile, currentAccount.toString() + "\r\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        /*
        System.out.println("Чтение из файла");
        StringBuilder readAccountFile = new StringBuilder();
        try {
            readAccountFile.append(Files.readString(accountFile));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(readAccountFile);

        System.out.println("Проверка регулярного выражения");
        ArrayList<Account> newAccounts = new ArrayList<>();
        Pattern accountPattern = Pattern.compile(Account.ACCOUNT_REGEXP);
        Matcher accountMatcher = accountPattern.matcher(readAccountFile);
        while (accountMatcher.find()) {
            newAccounts.add(new Account(accountMatcher.group()));
        }
        System.out.println("Вывод прочитанных счетов");
        for (Account currentAccount : newAccounts) System.out.println(currentAccount);
        System.out.println();
        */
        System.out.println("Создание платежных поручений");
        ArrayList<PaymentOrder> paymentOrders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            paymentOrders.add(new PaymentOrder(
                    /*Счет плательщика*/
                    accounts.get((int)(Math.random() * (accounts.size() - 1))).getNumber(),
                    /*Счет получателя*/
                    accounts.get((int)(Math.random() * (accounts.size() - 1))).getNumber(),
                    /*Сумма платежа*/
                    (int)(Math.random() * 2000)));
        }
        System.out.println("Запись в файл");
        Path paymentOrderFilePath = Path.of(PaymentOrder.DEFAULT_PATH);
        /*Создание директории*/
        if (Files.notExists(paymentOrderFilePath)) {
            try {
                Files.createDirectories(paymentOrderFilePath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        int i = 0;
        for (PaymentOrder currentPaymentOrder : paymentOrders) {
            /*Создание файла*/
            i++;
            Path paymentOrderFile = paymentOrderFilePath.resolve("paymentOrder" + i
                    + PaymentOrder.FILE_EXTENSION);
            try {
                Files.writeString(paymentOrderFile, "", StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
                Files.writeString(paymentOrderFile, currentPaymentOrder.toString(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        //Создание обработчика транзакций
        TransactionProcessor transactionProcessor = TransactionProcessor.getINSTANCE();
        transactionProcessor.executePaymentOrders();
        /*
        System.out.println("Чтение из файла");
        StringBuilder readPaymentOrderFile = new StringBuilder();
        try {
            readPaymentOrderFile.append(Files.readString(paymentOrderFile));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(readPaymentOrderFile);

        System.out.println("Проверка регулярного выражения");
        Pattern paymentOrderPattern = Pattern.compile(PaymentOrder.PAYMENT_ORDER_REGEXP);
        Matcher paymentOrderMatcher = paymentOrderPattern.matcher(readPaymentOrderFile);
        PaymentOrder readPO1 = null;
        if (paymentOrderMatcher.find()) {
            readPO1 = new PaymentOrder(paymentOrderMatcher.group());
        }
        System.out.println("Вывод прочитанного платежного поручения");
        if (readPO1 != null) System.out.println(readPO1);
        */
        /*
        System.out.println("Перемещение платежных поручений");

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
        //Получить список файлов
        ArrayList<Path> paymentOrderFiles = new ArrayList<>();
        System.out.println("Получение списка платежных поручений");
        try (Stream<Path> allFiles = Files.list(Path.of(PaymentOrder.DEFAULT_PATH))) {
            paymentOrderFiles = allFiles.filter(Files :: isRegularFile)
                    .map(Path :: getFileName)
                    //.filter(x -> x.getFileName().endsWith(PaymentOrder.PAYMENT_ORDER_FILE_EXTENSION))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (Path currentPaymentOrder : paymentOrderFiles) {
            Path inputFile = inputPath.resolve(currentPaymentOrder.getFileName());
            Path archiveFile = archivePath.resolve(currentPaymentOrder.getFileName());
            try {
                Files.move(inputFile, archiveFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }*/
    }
}
