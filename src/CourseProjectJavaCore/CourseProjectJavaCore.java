/**
 * Classname    CourceProjectJavaCore
 * @version     0.03
 * @author      Aleksei Borzetsov
 * date         02.05.2026
 */
 
package CourseProjectJavaCore;

import CourseProjectJavaCore.model.Account;
import CourseProjectJavaCore.model.PaymentOrder;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseProjectJavaCore {

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
        Path accountFilePath = Path.of("src\\CourseProjectJavaCore\\dataChannels\\");
        /*Создание директории*/
        if (Files.notExists(accountFilePath)) {
            try {
                Files.createDirectories(accountFilePath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        /*Создание файла*/
        Path accountFile = accountFilePath.resolve("accounts" + Account.ACCOUNT_DATABASE_FILE_EXTENSION);
        try {
            Files.createFile(accountFile);
            for (Account currentAccount : accounts)
                Files.writeString(accountFile, currentAccount.toString() + "\r\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

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

        System.out.println("Создание платежного поручения");
        PaymentOrder po1 = new PaymentOrder(newAccounts.get(0).getNumber(),
                newAccounts.get(1).getNumber(), 1000);

        System.out.println("Запись в файл");
        Path paymentOrderFilePath = Path.of("src\\CourseProjectJavaCore\\dataChannels\\input\\");
        /*Создание директории*/
        if (Files.notExists(paymentOrderFilePath)) {
            try {
                Files.createDirectories(paymentOrderFilePath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        /*Создание файла*/
        Path paymentOrderFile = paymentOrderFilePath.resolve("paymentOrder1"
                + PaymentOrder.PAYMENT_ORDER_FILE_EXTENSION);
        try {
            Files.createFile(paymentOrderFile);
            Files.writeString(paymentOrderFile, po1.toString(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

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

        System.out.println("Перемещение платежного поручения");

        Path inputPath = Path.of("src\\CourseProjectJavaCore\\dataChannels\\input\\");
        Path archivePath = Path.of("src\\CourseProjectJavaCore\\dataChannels\\archive\\");
        /*Создание директории*/
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
        Path inputFile = inputPath.resolve(paymentOrderFile.getFileName());
        Path archiveFile = archivePath.resolve(paymentOrderFile.getFileName());
        try {
            Files.move(inputFile, archiveFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
