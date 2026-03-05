import java.util.Scanner;

/**
 * Домашнее задание №2
 * @author Aleksei Borzetsov
 * 03/05/26
 */

public class Main {
    public static void main(String[] args){
/*
1. Задание состоит из четырех обязательных и одной дополнительной задачи.
2. Все задачи выполняются последовательно.
3. По каждой задаче составляется отчет.
4. Отчеты выводятся в консоль.
 */
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №2 | Выдано 02.03.2026 ;");
        System.out.println();

        /*Задача 1*/
        //Приглашение
        System.out.println("Задача №1\n\rВведите целое число");
        //Создать сканер
        Scanner consoleScanner1 = new Scanner(System.in);
        //Предварительная проверка
        if (consoleScanner1.hasNextInt()){
            int userValue1 = consoleScanner1.nextInt();
            if ((userValue1 % 2) == 0) System.out.println("Результат: введенное число является четным");
            else System.out.println("Результат: введенное число не является четным");
        }
        else{
            System.out.println("Результат: введенное значение не является целым числом");
        }
        System.out.println();

        /*Задача 2*/
        //Приглашение
        System.out.println("Задача №2\n\rВведите температуру");
        //Создать сканер
        Scanner consoleScanner2 = new Scanner(System.in);
        //Предварительная проверка
        if (consoleScanner2.hasNextInt()){
            int userValue2 = consoleScanner2.nextInt();
            if (userValue2 > -5){
                System.out.println("Результат: Warm");
            }
            else if ((userValue2 > -20) && (userValue2 <= -5)){
                System.out.println("Результат: Normal");
            }
            else{
                System.out.println("Результат: Cold");
            }
        }
        else if (consoleScanner2.hasNextDouble()){
            double userValue3 = consoleScanner2.nextDouble();
            if (userValue3 > -5){
                System.out.println("Результат: Warm");
            }
            else if ((userValue3 > -20) && (userValue3 <= -5)){
                System.out.println("Результат: Normal");
            }
            else{
                System.out.println("Результат: Cold");
            }
        }
        else{
            System.out.println("Результат: введенное значение не является числом");
        }
        System.out.println();

        /*Задача 3*/
        System.out.println("Задача №3\n\rКвадраты чисел [10..20]");
        for (int i = 10; i <= 20; i++){
            System.out.print(i * i + " ");
        }
        System.out.println();
        System.out.println();

        /*Задача 4*/
        System.out.println("Задача №4\n\rПоследовательность чисел");
        int val = 7;
        while(val < 100){
            System.out.print(val + " ");
            val += 7;
        }
        System.out.println();
        System.out.println();

        /*Задача **/
        //Приглашение
        System.out.println("Задача *\n\rВведите целое число");
        //Создать сканер
        Scanner consoleScanner3 = new Scanner(System.in);
        //Предварительная проверка
        if (consoleScanner3.hasNextInt()){
            int userValue3 = consoleScanner3.nextInt();
            if (userValue3 < 1) System.out.println("Результат: недопустимое значение");
            else{
                int sum = 0;
                for (int i = 1; i <= userValue3; i++) sum += i;
                System.out.println("Результат: сумма чисел [1.." + userValue3 + "] = " + sum);
            }
        }
        else{
            System.out.println("Результат: введенное значение не является целым числом");
        }
        System.out.println();
        System.out.println("Для демонстрации git merge");
    }
}
