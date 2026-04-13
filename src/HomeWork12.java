/**
 * Classname    HomeWork12
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         07.04.2026
 */

import javax.swing.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeWork12 {

    public void run() {
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №12 | Выдано 06.04.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        System.out.println();

        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("Введите строку");
        String userString = consoleScanner.nextLine();

        Pattern abbreviationPattern = Pattern.compile("\\b[A-ZА-Я]{2,6}\\b");  /*
                                                        ^    ^      ^   ^
                                                        |    |      |   Конец слова
                                                        |    |      Размер слова: 2-6 символов
                                                        |    Разрешенные символы
                                                        Начало слова  */
        Matcher abbreviationMatcher = abbreviationPattern.matcher(userString);
        StringBuilder answerString = new StringBuilder();

        while(abbreviationMatcher.find()) {
            //Добавить подстроку очередного совпадения
            answerString.append(userString, abbreviationMatcher.start(), abbreviationMatcher.end());
            //Добавить разделитель
            answerString.append(", ");
        }
        if (!answerString.isEmpty()) {
            //Убрать последний разделитель
            answerString.delete(answerString.length() - 2, answerString.length());
            System.out.println("Список аббревиатур:");
            System.out.println(answerString);
        }
        else {
            System.out.println("Аббревиатуры не обнаружены");
        }
        System.out.println();

        System.out.println("Задача *");
        System.out.println();

        System.out.println("Задача предполагает ввод текста.");
        System.out.println("Предложение <Конец текста.> означает конец ввода.");
        System.out.println("Введите текст");

        //Хранит весь текст
        StringBuilder userText = new StringBuilder();

        do {
            userString = consoleScanner.nextLine();
            userText.append(userString);
            userText.append(" ");
        } while (!userString.contains("Конец текста."));

        //Для поиска документов
        Pattern docIDPattern = Pattern.compile("\\b[0-9]{4}-[0-9]{4}-[0-9]{2}\\b");
        Matcher docIDMatcher = docIDPattern.matcher(userText);
        StringBuilder foundDocs = new StringBuilder();

        while(docIDMatcher.find()) {
            //Добавить подстроку очередного совпадения
            foundDocs.append(userText, docIDMatcher.start(), docIDMatcher.end());
            //Добавить разделитель
            foundDocs.append(", ");
        }
        if (!foundDocs.isEmpty()) {
            //Убрать последний разделитель
            foundDocs.delete(foundDocs.length() - 2, foundDocs.length());
            System.out.println("Список номеров документов:");
            System.out.println(foundDocs);
        }
        else {
            System.out.println("Номера документов не обнаружены");
        }
        System.out.println();

        //Для поиска номеров телефонов
        Pattern phonePattern = Pattern.compile("[+][(][0-9]{2}[)][0-9]{7}\\b");
        Matcher phoneMatcher = phonePattern.matcher(userText);
        StringBuilder foundPhones = new StringBuilder();

        while(phoneMatcher.find()) {
            //Добавить подстроку очередного совпадения
            foundPhones.append(userText, phoneMatcher.start(), phoneMatcher.end());
            //Добавить разделитель
            foundPhones.append(", ");
        }
        if (!foundPhones.isEmpty()) {
            //Убрать последний разделитель
            foundPhones.delete(foundPhones.length() - 2, foundPhones.length());
            System.out.println("Список номеров телефонов:");
            System.out.println(foundPhones);
        }
        else {
            System.out.println("Номера телефонов не обнаружены");
        }
        System.out.println();

        //Для поиска электронной почты
        Pattern emailPattern = Pattern.compile("\\b[0-9A-Za-zА-Яа-я._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");
        Matcher emailMatcher = emailPattern.matcher(userText);
        StringBuilder foundEmails = new StringBuilder();

        while(emailMatcher.find()) {
            //Добавить подстроку очередного совпадения
            foundEmails.append(userText, emailMatcher.start(), emailMatcher.end());
            //Добавить разделитель
            foundEmails.append(", ");
        }
        if (!foundEmails.isEmpty()) {
            //Убрать последний разделитель
            foundEmails.delete(foundEmails.length() - 2, foundEmails.length());
            System.out.println("Список адресов электронной почты:");
            System.out.println(foundEmails);
        }
        else {
            System.out.println("Адресов электронной почты не обнаружены");
        }
        System.out.println();
    }
}
