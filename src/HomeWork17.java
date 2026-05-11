/**
 * Classname    HomeWork17
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         09.05.2026
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class HomeWork17 {

    public void run() {
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №17 | Выдано 27.04.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        Scanner consoleScanner = new Scanner(System.in);
        boolean task1Done = false;
        do {
            System.out.println("Введите дату рождения в формате дд.мм.гггг");
            if (consoleScanner.hasNextLine())
            {
                String userInput = consoleScanner.nextLine();
                try {
                    LocalDate userBirthDay = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    LocalDate user100yo = userBirthDay.plusYears(100);
                    System.out.println("Сотый день рождения: "
                            + user100yo.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    System.out.println();
                    task1Done = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Ошибка формата даты");
                }
            }
        } while (!task1Done);

        System.out.println("Задача 2");
        int[] randomIntArray = new int[20];
        double currentRandom;
        for (int i = 0; i < 20; i++) {
            currentRandom = Math.random();
            randomIntArray[i] = (int)(100 * currentRandom - 50);
        }
        System.out.println("Массив значений:");
        System.out.println(Arrays.toString(randomIntArray));

        ArrayList<Integer> positiveInts = new ArrayList<>();
        Predicate<Integer> isPositive = x -> x >= 0;
        for (Integer currentInt : randomIntArray) {
            if (isPositive.test(currentInt)) positiveInts.add(currentInt);
        }
        System.out.println("Положительные из них:");
        System.out.println(positiveInts);
        System.out.println();

        System.out.println("Задача 3");
        boolean task3Done = false;
        //1 $ = 74,05 руб
        Function <String, Double> parse = x -> Double.parseDouble(x) / 74.05;
        do {
            System.out.println("Введите сумму в рублях");
            if (consoleScanner.hasNextLine())
            {
                String userInput = consoleScanner.nextLine();
                try {
                    double userInputDollars = parse.apply(userInput);
                    System.out.printf("Сумма в долларах: %.2f\r\n", userInputDollars);
                    System.out.println();
                    task3Done = true;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка формата суммы");
                }
            }
        } while (!task3Done);

        System.out.println("Задача 4");
        boolean task4Done = false;
        //1 $ = 74,05 руб
        Consumer<String> converter = x ->
                System.out.printf("Сумма в долларах: %.2f\r\n", (Double.parseDouble(x) / 74.05));
        do {
            System.out.println("Введите сумму в рублях");
            if (consoleScanner.hasNextLine())
            {
                String userInput = consoleScanner.nextLine();
                try {
                    converter.accept(userInput);
                    System.out.println();
                    task4Done = true;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка формата суммы");
                }
            }
        } while (!task4Done);

        System.out.println("Задача 5");
        System.out.println("Введите строку");
        Supplier<String> reverser = () -> {
            StringBuilder userInputStringBuilder = new StringBuilder(consoleScanner.nextLine());
            return userInputStringBuilder.reverse().toString();
        };
        System.out.println("Строка наоборот:\r\n" + reverser.get());
    }
}
