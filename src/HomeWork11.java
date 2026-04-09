/**
 * Classname    HomeWork11
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         04.04.2026
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class HomeWork11 {

    public void run() {

        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №11 | Выдано 01.04.2026 ;");
        System.out.println();

        Scanner consoleScanner = new Scanner(System.in);

        /*Тестовые строки, чтобы не затягивать тестирование*/
        /*String str1 = "Ввести 3 строки с консоли";
        String str2 = "Преобразовать в String и вывести в консоль";
        String str3 = "Перевернуть строку строку";
        String[] strings = {str1, str2, str3};*/

        String[] strings = new String[3];
        System.out.println("Введите 3 строки");
        for (int i = 0; i < 3; i++) {
            strings[i] = consoleScanner.nextLine();
        }
        System.out.println();

        System.out.println("Задача 1");
        System.out.println();

        //Параметр сравнения -- длина строк
        Arrays.sort(strings, Comparator.comparingInt(String::length));
        System.out.println("Самая короткая строка:");
        System.out.println(strings[0] + " //Длина: " + strings[0].length());
        System.out.println("Самая длинная строка:");
        System.out.println(strings[strings.length - 1] + " //Длина: " + strings[strings.length - 1].length());
        System.out.println();

        System.out.println("Задача 2");
        System.out.println("Строки в порядке возрастания длин:");
        for (String string : strings) {
            System.out.println(string + " //Длина: " + string.length());
        }
        System.out.println();

        System.out.println("Задача 3");
        //Определить среднюю длину строк
        int avrStringLength = 0;
        for (String string : strings) {
            avrStringLength += string.length();
        }
        avrStringLength /= strings.length;
        System.out.println("Средняя длина строк: " + avrStringLength);
        System.out.println("Строки, длина которых меньше средней:");
        for (String string : strings) {
            if (string.length() < avrStringLength) {
                System.out.println(string + " //Длина: " + string.length());
            }
        }
        System.out.println();

        System.out.println("Задача 4");
        System.out.println("Первое слово, в котором символы не повторяются:");
        boolean foundTheWord = false;
        for (String currentString : strings) {
            if (foundTheWord) break;
            String[] splitedString = currentString.split(" ");
            for (String currentWord : splitedString) {
                if (foundTheWord) break;
                if (!hasDuplicate(currentWord)) {
                    System.out.println(currentWord);
                    foundTheWord = true;
                }
            }
        }
        System.out.println();

        System.out.println("Задача 5");
        System.out.println("Введите строку");
        String userString = consoleScanner.nextLine();

        System.out.println("Начальная строка:");
        System.out.println(userString);

        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < userString.length(); i++) {
            newString.append(userString.charAt(i));
            newString.append(userString.charAt(i));
        }

        System.out.println("Новая строка:");
        System.out.println(newString);
        System.out.println();

        System.out.println("Задача *");
        System.out.println("Введите строку");
        userString = consoleScanner.nextLine();
        String[] userStringWords = userString.split(" ");
        int userNumber = 1;

        boolean waitingForNumber = true;
        do{
            System.out.println("Введите номер слова");
            if (consoleScanner.hasNextLine()){
                if (consoleScanner.hasNextInt()) {
                    int tmp = consoleScanner.nextInt();
                    if (tmp > 0) {
                        if (tmp < userStringWords.length) {
                            userNumber = tmp;
                            waitingForNumber = false;
                        }
                        else {
                            System.out.print("Слова с таким номером не существует, ");
                        }
                    }
                    else {
                        System.out.print("Ошибка номера слова, ");
                    }
                }
                else {
                    System.out.print("Введенное значение не является целым числом, ");
                }
                //Сбросить содержимое сканера
                consoleScanner.nextLine();
            }
        }while(waitingForNumber);

        System.out.println(Arrays.toString(userStringWords));
        System.out.print("Слово \"" + userStringWords[userNumber - 1] + "\" ");
        if (isPalindrome(userStringWords[userNumber - 1])) {
            System.out.println("является палиндромом.");
        }
        else {
            System.out.println("не является палиндромом.");
        }
    }

    /**
     * Проверяет, есть ли в строке повторяющиеся символы.
     * Длина от двух символов
     * @param currentString исследуемая строка
     * @return  статус
     */
    private boolean hasDuplicate(String currentString) {
        //Длина от двух символов
        if (currentString.length() < 2) return true;
        /*Если число уникальных символов меньше длины строки,
          то есть повторяющиеся символы*/
        return currentString.chars().distinct().count() < currentString.length();
    }

    /**
     * Проверяет, является ли строка палиндромом
     * @param currentString исследуемая строка
     * @return  статус
     */
    private boolean isPalindrome(String currentString) {
        StringBuilder reverseString = new StringBuilder(currentString).reverse();
        return currentString.equalsIgnoreCase(reverseString.toString());
    }
}
