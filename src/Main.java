import java.util.Arrays;
import java.util.Scanner;

/**
 * Домашнее задание №4
 * @author Aleksei Borzetsov
 * 03/11/26
 */

public class Main {
    public static void main(String[] args){
/*
1. Задание состоит из шести обязательных и одной дополнительной задачи.
2. Все задачи выполняются последовательно.
3. Все задачи используют один набор входных данных.
4. Входные данные вводятся пользователем или генерируются случайно.
5. По каждой задаче составляется отчет.
6. Отчеты выводятся в консоль.
 */
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №4 | Выдано 09.03.2026 ;\r\n");

        /*Создание сканера*/
        Scanner consoleScanner = new Scanner(System.in);
        int arrayLength = 0;

        /*Приглашение*/
        System.out.println("Задание предполагает работу с массивом.");
        do{
            System.out.println("Введите размер массива");
            if (consoleScanner.hasNextInt()) arrayLength = consoleScanner.nextInt();
        }while(arrayLength <= 0);

        /*Инициализация массива*/
        int[] array = new int[arrayLength];
        String userInput;
        System.out.println("Заполнить массив случайными значениями [y/n]?");
        do{
            userInput = consoleScanner.nextLine().toLowerCase();
        } while((!userInput.equals("y")) && (!userInput.equals("n")));

        if (userInput.equals("y")){
            for(int i = 0; i < array.length; i++) array[i] = (int)(Math.random() * 100);
        }
        else {
            int currentIndex = 0;
            do{
                System.out.println("Введите целое число");
                if (consoleScanner.hasNextLine()){
                    if (consoleScanner.hasNextInt()) {
                        array[currentIndex] = consoleScanner.nextInt();
                        currentIndex++;
                    }
                    else {
                        System.out.print("Введенное значение не является целым числом, ");
                    }
                    //Сбросить содержимое сканера
                    consoleScanner.nextLine();
                }
            }while(currentIndex < array.length);
        }
        System.out.println("\r\nНачальное состояние массива:");
        System.out.println(Arrays.toString(array) + "\r\n");
    }
}
