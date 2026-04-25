/**
 * Classname    CourceProjectJavaCore
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         21.04.2026
 */
 
package CourseProjectJavaCore;

import CourseProjectJavaCore.model.Account;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            currentAccount.income((long)(Math.random() * 1000000L));
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
            while((currentChar = accountReader.read()) != -1) {
                readAccountFile.append((char)currentChar);
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
    }
}
