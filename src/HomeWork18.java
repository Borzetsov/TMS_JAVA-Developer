/**
 * Classname    HomeWork18
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         09.05.2026
 */

import java.util.ArrayList;

public class HomeWork18 {

    public void run() {
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №18 | Выдано 29.04.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integerArrayList.add((int)(Math.random() * 100));
        }
        System.out.println("Начальный массив:");
        System.out.println(integerArrayList);
        int intStream = integerArrayList.stream()   //Преобразовать в поток
                .distinct()                         //Исключить дубликаты
                .filter(x -> x % 2 == 0)     //Оставить только четные
                .mapToInt(Integer :: intValue)      //Преобразовать в примитивы
                .sum();                             //Посчитать сумму
        System.out.println("Сумма уникальных четных чисел: " + intStream);
    }
}
