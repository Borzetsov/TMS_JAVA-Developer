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
        } while (!userString.contains("Конец текста."));

        //Для поиска документов
        Pattern docIDPattern = Pattern.compile("");
        Matcher docIDMatcher = docIDPattern.matcher(userText);
        StringBuilder foundDocs = new StringBuilder();

        //Для поиска номеров телефонов
        Pattern phonePattern = Pattern.compile("");
        Matcher phoneMatcher = phonePattern.matcher(userText);
        StringBuilder foundPhones = new StringBuilder();

        //Для поиска электронной почты
        Pattern emailPattern = Pattern.compile("");
        Matcher emailMatcher = emailPattern.matcher(userText);
        StringBuilder foundEmails = new StringBuilder();
    }
}
