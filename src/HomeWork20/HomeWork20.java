/**
 * Classname    HomeWork20.HomeWork20
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         10.05.2026
 */

package HomeWork20;

import java.util.Scanner;

public class HomeWork20 {

    public void run() {
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №20 | Выдано 06.05.2026 ;");
        System.out.println();

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
            for(int i = 0; i < array.length; i++) array[i] = (int)(Math.random() * 100 - Math.random() * 50);
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
        System.out.println();
        System.out.println("Начальное состояние массива:");
        //Вывод массива в читаемом виде
        System.out.print("[");
        for (int i = 0; i < array.length - 1; i++){
            System.out.printf("%2d, ", array[i]);
        }
        System.out.printf("%2d]", array[arrayLength - 1]);
        System.out.println();
        System.out.println();

        System.out.println("Задача 1");
        MaxFinder maxFinder = new MaxFinder(array);
        MinFinder minFinder = new MinFinder(array);
        Thread maxFinderThread = new Thread(maxFinder);
        Thread minFinderThread = new Thread(minFinder);
        maxFinderThread.start();
        minFinderThread.start();
        //Ожидание завершения потоков
        try {
            maxFinderThread.join(100);
            minFinderThread.join(100);
            System.out.println("Максимальное число: " + maxFinder.getResult());
            System.out.println("Минимальное число: " + minFinder.getResult());
        } catch (InterruptedException e) {
            System.out.println("Прерывание ожидания");
        }
        System.out.println();
    }
}
