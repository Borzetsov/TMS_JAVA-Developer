/**
 * Classname    CourceProjectJavaCore
 * @version     0.02
 * @author      Aleksei Borzetsov
 * date         28.04.2026
 */
 
package CourseProjectJavaCore;

import CourseProjectJavaCore.model.Account;
import CourseProjectJavaCore.model.PaymentOrder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Переписать всю работу с файлами на API NIO

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
        File accountFilePath = new File("src\\CourseProjectJavaCore\\dataChannels\\");
        File accountFile = new File(accountFilePath, "accounts.txt");

        try (FileWriter accountWriter = new FileWriter(accountFile)) {
            for (Account currentAccount : accounts) accountWriter.write(currentAccount.toString() + "\r\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Чтение из файла");
        StringBuilder readAccountFile = new StringBuilder();
        try (FileReader accountReader = new FileReader(accountFile)) {
            int currentChar;
            while ((currentChar = accountReader.read()) != -1) {
                readAccountFile.append((char) currentChar);
            }
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
        File paymentOrderFilePath = new File("src\\CourseProjectJavaCore\\dataChannels\\input\\");
        File paymentOrderFile = new File(paymentOrderFilePath, "paymentOrder1.txt");
        try (FileWriter paymentOrderWriter = new FileWriter(paymentOrderFile)) {
            paymentOrderWriter.write(po1.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Чтение из файла");
        StringBuilder readPaymentOrderFile = new StringBuilder();
        try (FileReader paymentOrderReader = new FileReader(paymentOrderFile)) {
            int currentChar;
            while ((currentChar = paymentOrderReader.read()) != -1) {
                readPaymentOrderFile.append((char) currentChar);
            }
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
        Path inputPath = Paths.get("src\\CourseProjectJavaCore\\dataChannels\\input\\"
                + paymentOrderFile.getName());
        Path archivePath = Paths.get("src\\CourseProjectJavaCore\\dataChannels\\archive\\"
                + paymentOrderFile.getName());
        try {
            Files.move(inputPath, archivePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
