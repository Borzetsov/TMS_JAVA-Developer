/**
 * Classname    HomeWork15
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         17.04.2026
 */
 
package HomeWork15;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeWork15 {

    public void run() {
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №15 | Выдано 15.04.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        System.out.println();

        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("Введите набор чисел");
        String userInput = consoleScanner.nextLine();
        /*Получить список уникальных чисел*/
        Set<Integer> userInputIntegerSet = new HashSet<>();
        Pattern intPattern = Pattern.compile("\\b[0-9]+\\b");
        Matcher intMatcher = intPattern.matcher(userInput);
        while (intMatcher.find()) {
            userInputIntegerSet.add(Integer
                    .valueOf(userInput.substring(intMatcher.start(), intMatcher.end())));
        }
        if (!userInputIntegerSet.isEmpty()) {
            System.out.println("Коллекция уникальных чисел:");
            System.out.println(userInputIntegerSet);
        }
        else {
            System.out.println("Чисел не обнаружено");
        }
        System.out.println();

        System.out.println("Задача 2");
        System.out.println();

        Animals animals = new Animals();
        animals.addAnimal("Собака");
        animals.addAnimal("Кошка");
        animals.addAnimal("Медведь");
        animals.addAnimal("Лошадь");
        System.out.println(animals);
        animals.removeAnimal();
        animals.addAnimal("Собака");
        System.out.println(animals);
        System.out.println();
    }
}
