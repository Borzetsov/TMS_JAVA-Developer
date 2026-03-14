import java.util.Arrays;
import java.util.Scanner;

/**
 * Домашнее задание №4
 * @author Aleksei Borzetsov
 * 03/13/26
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

        /*Задача 1*/
        System.out.println("Задача 1");
        System.out.println("Вывод массива в прямом порядке:");
        //Вывод массива в читаемом виде
        System.out.print("[");
        for (int i = 0; i < array.length - 1; i++){
            System.out.printf("%2d, ", array[i]);
        }
        System.out.printf("%2d]", array[arrayLength - 1]);
        System.out.println();

        System.out.println("Вывод массива в обратном порядке:");
        System.out.print("[");
        for (int i = array.length - 1; i > 0 ; i--){
            System.out.printf("%2d, ", array[i]);
        }
        System.out.printf("%2d]", array[0]);
        System.out.println();
        System.out.println();

        /*Задача 2*/
        System.out.println("Задача 2");
        //Вывод массива в читаемом виде
        System.out.print("[");
        for (int i = 0; i < array.length - 1; i++){
            System.out.printf("%2d, ", array[i]);
        }
        System.out.printf("%2d]", array[arrayLength - 1]);
        System.out.println();
        /*Определение максимального и минимального значений вместе с индексами (для Задачи 3)*/
        int indexLow = 0, indexHigh = 0;
        int valLow = array[0], valHigh = array[0];
        //Поиск индексов
        for (int i = 0; i < arrayLength; i++){
            //Младший элемент
            if (array[i] < valLow){
                valLow = array[i];
                indexLow = i;
            }
            //Старший элемент
            if (array[i] > valHigh){
                valHigh = array[i];
                indexHigh = i;
            }
        }
        System.out.println("Минимальный элемент: " + valLow + ", максимальный: " + valHigh);
        System.out.println();
        /* Альтарнатива
        //Объявить сортированный массив
        int[] tmpArray = new int[arrayLength];
        //Скопировать изначальный массив
        System.arraycopy(array, 0, tmpArray, 0, arrayLength);
        //Произвести сортировку
        Arrays.sort(tmpArray);
        System.out.println("Минимальный элемент: " + tmpArray[0] + " максимальный: " + tmpArray[arrayLength - 1]);
        System.out.println();*/

        /*Задача 3*/
        System.out.println("Задача 3");
        //Отображение индексов
        System.out.print("  0");
        for (int i = 1; i < arrayLength; i++){
            System.out.printf("%4d", i);
        }
        System.out.println();
        //Вывод массива в читаемом виде
        System.out.print("[");
        for (int i = 0; i < array.length - 1; i++){
            System.out.printf("%2d, ", array[i]);
        }
        System.out.printf("%2d]", array[arrayLength - 1]);
        System.out.println();

        System.out.println("Индекс минимального элемента: " + indexLow + ", максимального: " + indexHigh);
        System.out.println();

        /*Задача 4*/
        System.out.println("Задача 4");
        //Вывод массива в читаемом виде
        System.out.print("[");
        for (int i = 0; i < array.length - 1; i++){
            System.out.printf("%2d, ", array[i]);
        }
        System.out.printf("%2d]", array[arrayLength - 1]);
        System.out.println();
        int zeroValAmount = 0;
        for (int i = 0; i < arrayLength; i++){
            if (array[i] == 0) zeroValAmount++;
        }
        if (zeroValAmount != 0) System.out.println("Нулевых элементов в рабочем массиве: " + zeroValAmount);
        else System.out.println("Нулевых элементов в рабочем массиве не обнаружено");
        System.out.println();

        /*Задача 5*/
        System.out.println("Задача 5");
        //Объявить зеркальный массив
        int[] mirrorArray = new int[arrayLength];
        //Скопировать изначальный массив
        System.arraycopy(array, 0, mirrorArray, 0, arrayLength);
        System.out.println("Начальное состояние массива:");
        //Вывод массива в читаемом виде
        System.out.print("[");
        for (int i = 0; i < array.length - 1; i++){
            System.out.printf("%2d, ", array[i]);
        }
        System.out.printf("%2d]", array[arrayLength - 1]);
        System.out.println();
        /*Определить количество возможных перестановок*/
        int switchAmount = (int)(arrayLength / 2);
        int tmp = 0;        //Временное хранилище
        //Отзеркалить массив
        for (int i = 0, j = arrayLength - 1; i < switchAmount; i++, j--){
            tmp = mirrorArray[i];
            mirrorArray[i] = mirrorArray[j];
            mirrorArray[j] = tmp;
        }
        System.out.println("Конечное состояние массива:");
        //Вывод массива в читаемом виде
        System.out.print("[");
        for (int i = 0; i < array.length - 1; i++){
            System.out.printf("%2d, ", mirrorArray[i]);
        }
        System.out.printf("%2d]", mirrorArray[arrayLength - 1]);
        System.out.println();
        System.out.println();

        /*Задача 6*/
        System.out.println("Задача 6");
        //Вывод массива в читаемом виде
        System.out.print("[");
        for (int i = 0; i < array.length - 1; i++){
            System.out.printf("%2d, ", array[i]);
        }
        System.out.printf("%2d]", array[arrayLength - 1]);
        System.out.println();
        //Считаем, что массив является возрастающей последоватеьностью
        boolean isArrayRising = true;
        for (int i = 0; i < arrayLength - 1; i++){
            if (array[i] > array[i + 1]){
                //Достаточно один раз нарушить условие
                isArrayRising = false;
                //Дальше проверять незачем
                break;
            }
        }
        if (isArrayRising) System.out.println("Рабочий массив является возрастающей последовательностью");
        else System.out.println("Рабочий массив не является возрастающей последовательностью");
        System.out.println();

        /*Задача **/
        System.out.println("Задача *");
        System.out.println("Задача предполагает работу со строковым представлением целого числа");
        //По условиям задачи число положительное. Если отрицательное, значит ещё не ввели
        int initialValue = -1;
        System.out.println("Создать случайное число [y/n]?");
        do{
            userInput = consoleScanner.nextLine().toLowerCase();
        } while((!userInput.equals("y")) && (!userInput.equals("n")));

        if (userInput.equals("y")){
            initialValue = (int)(Math.random() * 10000);
        }
        else {
            do{
                System.out.println("Введите целое положительное число");
                if (consoleScanner.hasNextLine()){
                    if (consoleScanner.hasNextInt()) {
                        initialValue = consoleScanner.nextInt();
                        if (initialValue <  0) System.out.print("Неверное значение, ");
                    }
                    else {
                        System.out.print("Введенное значение не является числом, ");
                    }
                    //Сбросить содержимое сканера
                    consoleScanner.nextLine();
                }
            }while(initialValue < 0);
        }
        System.out.println("Число: " + initialValue);
        /*Представить число в виде массива символов*/
        char[] charView = String.valueOf(initialValue).toCharArray();
        /*Объявить начальный массив */
        int[] initialArray = new int[charView.length];
        for(int i = 0; i < charView.length; i++){
            //Перевод из ASCII в int каждого символа
            initialArray[i] = charView[i] - '0';
        }
        System.out.println("Строковое представление числа:");
        System.out.println(Arrays.toString(initialArray));
        /*Добавить к младшему разряду единицу*/
        initialArray[initialArray.length - 1] ++;
        /*Цикл проверок с переносом*/
        for (int i = initialArray.length - 1; i > 0; i--){
            if (initialArray[i] > 9){
                initialArray[i] = 0;
                initialArray[i - 1] ++;
            }
        }
        /*Объявить конечный массив*/
        int[] finalArray;
        /*Проверка старшего разряда*/
        if (initialArray[0] > 9){
            initialArray[0] = 0;
            //Возникло переполнение -- увеличить размер массива на 1
            finalArray = new int[initialArray.length + 1];
            System.arraycopy(initialArray, 0, finalArray, 1, initialArray.length);
            finalArray[0] = 1;
        }
        else{
            finalArray = new int[initialArray.length];
            System.arraycopy(initialArray, 0, finalArray, 0, initialArray.length);
        }
        System.out.println("Добавили единицу");
        System.out.println("Строковое представление числа:");
        System.out.println(Arrays.toString(finalArray));
    }
}
