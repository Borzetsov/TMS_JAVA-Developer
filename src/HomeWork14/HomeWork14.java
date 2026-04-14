/**
 * Classname    HomeWork14
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         14.04.2026
 */
 
package HomeWork14;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeWork14 {

    public void run() {
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №14 | Выдано 13.04.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        /*Чтение файла*/
        File filePath = new File("src\\HomeWork14\\Task1\\");
        File inputFile = new File(filePath, "RnJ_Synopsis.txt");
        try (FileReader inputFileReader = new FileReader(inputFile)) {
            StringBuilder inputText = new StringBuilder();
            /*Составить текст*/
            int currentChar;
            while((currentChar = inputFileReader.read()) != -1) {
                inputText.append((char)currentChar);
            }
            /*Вычленить слова*/
            Pattern wordPattern = Pattern.compile("\\b[\\w]+\\b");  //Любое слово
            Matcher wordMatcher = wordPattern.matcher(inputText);
            StringBuilder cleanText = new StringBuilder();
            while (wordMatcher.find()) {
                cleanText.append(inputText.subSequence(wordMatcher.start(), wordMatcher.end()));
                cleanText.append(" ");  //Разделитель
            }
            /*Разделить текст на слова*/
            String[] cplitString = cleanText.toString().split(" ");
            /*Найти самое длинное слово*/
            String theLongestWord = "";
            for (String currentString : cplitString) {
                if (currentString.length() > theLongestWord.length()) {
                    theLongestWord = currentString;
                }
            }
            File outputFile = new File(filePath, "RnJ_TheLongestWord.txt");
            try (FileWriter outputFileWriter = new FileWriter(outputFile)) {
                outputFileWriter.write("The longest word of the text is: " + theLongestWord);
            } catch (IOException e) {
                System.out.println("Ошибка записи в файл");
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
            System.out.println(e.getMessage());
        }
        System.out.println("Задача 1 выполнена");
        System.out.println();

        System.out.println("Задача *");
        System.out.println("Введите '+', чтобы создать файл test.txt");
        System.out.println("или");
        System.out.println("Введите произвольный символ, чтобы пропустить шаг");
        Scanner consoleScanner = new Scanner(System.in);
        if (consoleScanner.nextLine().equals("+")) {
            /*Создать файл*/
            File testFilePath = new File("src\\Homework14\\TaskAsterisk\\");
            File test = new File(testFilePath, "test.txt");
            try (FileWriter testWriter = new FileWriter(test)) {
                /*Заполнить тестовый файл*/
                double d;
                String radnomDocNumber;
                StringBuilder validDocNumber;
                for (int i = 0; i < 100; i++) {
                    d = Math.random();
                    if (d < 0.33) {
                        radnomDocNumber = String.valueOf((int)(Math.random() * 1000000000));
                        validDocNumber = new StringBuilder("docnum000000000");
                        validDocNumber.replace(15 - radnomDocNumber.length(), 16, radnomDocNumber);
                        testWriter.write(validDocNumber + "\r\n");
                    }
                    else if (d < 0.66) {
                        radnomDocNumber = String.valueOf((int)(Math.random() * 10000000));
                        validDocNumber = new StringBuilder("contract0000000");
                        validDocNumber.replace(15 - radnomDocNumber.length(), 16, radnomDocNumber);
                        testWriter.write(validDocNumber + "\r\n");
                    }
                    else {
                        testWriter.write("invalid contract " + i + "\r\n");
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка записи в файл");
                System.out.println(e.getMessage());
            }
            System.out.println("Тестовый файл создан");
        }
        System.out.println("Укажите путь к файлу");
        File userFile = new File(consoleScanner.nextLine());
        try (FileReader userFileReader = new FileReader(userFile)) {
            StringBuilder userText = new StringBuilder();
            /*Чтение пользовательского файла*/
            int currentChar;
            while((currentChar = userFileReader.read()) != -1) userText.append((char)currentChar);
            /*Разделить по строкам*/
            String[] splitUserText = userText.toString().split("\r\n");

            StringBuilder validDocNumberSB = new StringBuilder();
            StringBuilder invalidDocNumberSB = new StringBuilder();

            Pattern validDocNumPattern = Pattern.compile("^docnum[a-zA-Z0-9]{9}|^contrac[a-zA-Z0-9]{7}");
            Matcher validDocNumMatcher;
            for (String currentString : splitUserText) {
                /*Проверка каждой строки*/
                validDocNumMatcher = validDocNumPattern.matcher(currentString);
                if (validDocNumMatcher.find()) {
                    validDocNumberSB.append(currentString);
                    validDocNumberSB.append("\r\n");
                }
                else {
                    invalidDocNumberSB.append(currentString);
                    /*Поиск причины*/
                    invalidDocNumberSB.append("\t\tПричины:");
                    if (currentString.length() != 15) invalidDocNumberSB.append("\tошибка длины");
                    if (!(currentString.startsWith("docnum") || currentString.startsWith("contrac")))
                        invalidDocNumberSB.append("\tошибка названия");
                    else invalidDocNumberSB.append("\tсодержит запрещенные символы");
                    invalidDocNumberSB.append("\r\n");
                }
            }
            /*Сохранить файлы отчетов в ту же директорию*/
            String userFilePath = userFile.getParent();
            File validDocNumFile = new File(userFilePath, "validDocumentNumbers.txt");
            File invalidDocNumFile = new File(userFilePath, "invalidDocumentNumbers.txt");
            /*Записать валидные номера документов*/
            try (FileWriter validDocNumWriter = new FileWriter(validDocNumFile)) {
                validDocNumWriter.write(validDocNumberSB.toString());
            } catch (IOException e) {
                System.out.println("Ошибка создания файла");
                System.out.println(e.getMessage());
            }
            /*Записать невалидные номера документов*/
            try (FileWriter invalidDocNumWriter = new FileWriter(invalidDocNumFile)) {
                invalidDocNumWriter.write(invalidDocNumberSB.toString());
            } catch (IOException e) {
                System.out.println("Ошибка создания файла");
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
            System.out.println(e.getMessage());
        }
        System.out.println("Задача * выполнена");
    }
}
