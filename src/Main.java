import java.util.Scanner;

/**
 * Домашнее задание №5
 * @author Aleksei Borzetsov
 * 03/15/26
 */

public class Main {
    public static void main(String[] args){
/*
1. Задание состоит из двух обязательных и одной дополнительной задачи.
2. Все задачи выполняются последовательно.
3. Входные данные вводятся пользователем.
4. По каждой задаче составляется отчет.
5. Отчеты выводятся в консоль.
 */
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №5 | Выдано 11.03.2026 ;");
        System.out.println();

        /*Создание сканера*/
        Scanner consoleScanner = new Scanner(System.in);

        /*============================================================================================================*/
        System.out.println("Задача 1");
        int arrayLengthX = 0;
        int arrayLengthY = 0;

        /*Приглашение*/
        System.out.println("Задание предполагает работу с двумерным массивом.");
        do {
            System.out.println("Введите первую размерность");
            if (consoleScanner.hasNextInt()) arrayLengthX = consoleScanner.nextInt();
            consoleScanner.nextLine();      //Очистить буфер сканера
        } while(arrayLengthX <= 0);
        do {
            System.out.println("Введите вторую размерность");
            if (consoleScanner.hasNextInt()) arrayLengthY = consoleScanner.nextInt();
            consoleScanner.nextLine();      //Очистить буфер сканера
        } while(arrayLengthY <= 0);

        /*Создание массива случайных чисел*/
        int[][] array = new int[arrayLengthX][arrayLengthY];
        for (int i = 0; i < arrayLengthX; i++){
            for (int j = 0; j < arrayLengthY; j++){
                array[i][j] = (int)(Math.random() * 100);
            }
        }
        /*Вывод массива в читаемом виде*/
        System.out.println("Начальное состояние массива:");
        for (int i = 0; i < arrayLengthX; i++){
            System.out.print("[");
            for (int j = 0; j < arrayLengthY - 1; j++){
                System.out.printf("%3d, ", array[i][j]);
            }
            System.out.printf("%3d]\r\n", array[i][arrayLengthY - 1]);
        }
        System.out.println();

        int incValue = -1;
        System.out.println("Задание предполагает увеличение всех элементов на заданное число.");
        do {
            System.out.println("Введите целое число");
            if (consoleScanner.hasNextInt()) incValue = consoleScanner.nextInt();
            consoleScanner.nextLine();      //Очистить буфер сканера
        } while(incValue < 0);

        /*Изменение элементов массива*/
        for (int i = 0; i < arrayLengthX; i++){
            for (int j = 0; j < arrayLengthY; j++){
                array[i][j] += incValue;
            }
        }
        /*Вывод массива в читаемом виде*/
        System.out.println("Конечное состояние массива:");
        for (int i = 0; i < arrayLengthX; i++){
            System.out.print("[");
            for (int j = 0; j < arrayLengthY - 1; j++){
                System.out.printf("%3d, ", array[i][j]);
            }
            System.out.printf("%3d]\r\n", array[i][arrayLengthY - 1]);
        }
        System.out.println();
        /*Вычисление суммы всех элементов конечного массива*/
        long arrayItemSum = 0L;
        for (int[] currentRow : array){
            for (int currentItem : currentRow){
                arrayItemSum += currentItem;
            }
        }
        System.out.println("Сумма всех элементов: " + arrayItemSum);
        System.out.println();

        /*============================================================================================================*/
        System.out.println("Задача 2");
        System.out.println("Раскраска шахматной доски:");
        /*Создание массива*/
        String[][] chessBoardArray = new String[8][8];
        /*Заполнение (раскраска)*/
        String firstSquare, secondSquare;
        for (int i = 0; i < 8; i++){
            /*Если строка четная, то начинается с белой клетки, иначе -- с черной*/
            if (i % 2 == 0){
                firstSquare = "W";
                secondSquare = "B";
            }
            else{
                firstSquare = "B";
                secondSquare = "W";
            }
            for (int j = 0; j < 8; j++){
                /*Если столбец четный, клетка первого цвета, иначе -- второго*/
                chessBoardArray[i][j] = (j % 2 == 0) ? firstSquare : secondSquare;
            }
        }
        /*Вывод массива в читаемом виде*/
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                System.out.printf("%3s", chessBoardArray[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        /*============================================================================================================*/
        System.out.println("Задача *");
        int elementsTotal;
        arrayLengthX = -1;
        arrayLengthY = -1;
        System.out.println("Задача предполагает заполнение двумерного массива \"змейкой\"");
        do {
            do {
                System.out.println("Введите первую размерность");
                if (consoleScanner.hasNextInt()) arrayLengthX = consoleScanner.nextInt();
                consoleScanner.nextLine();      //Очистить буфер сканера
            } while (arrayLengthX <= 0);
            do {
                System.out.println("Введите вторую размерность");
                if (consoleScanner.hasNextInt()) arrayLengthY = consoleScanner.nextInt();
                consoleScanner.nextLine();      //Очистить буфер сканера
            } while (arrayLengthY <= 0);
            /*Проверка входных данных (в массиве должно быть не более 1000 элементов*/
            elementsTotal = arrayLengthX * arrayLengthY;
            if ((elementsTotal < 0) || (elementsTotal > 1000)) System.out.println("Ошибка размерности массива");
        } while((elementsTotal < 0) || (elementsTotal > 1000));
        /*Создание массива*/
        int[][] snakeArray = new int[arrayLengthX][arrayLengthY];
        /*Заполнение массива*/
        int rowValStart = 0;        //С какого значения начинается строка
        int rowValDelta = 1;        //Как меняется значение следующего элемента в строке
        for (int i = 0; i < arrayLengthX; i++){
            for (int j = 0; j < arrayLengthY; j++){
                snakeArray[i][j] = rowValStart;
                rowValStart += rowValDelta;
            }
            //Определить начальное значение следующей строки
            if (i % 2 == 0) rowValStart += arrayLengthY - 1;        //Следующая строка будет четной
            else rowValStart = (i + 1) * arrayLengthY;              //Следующая строка будет нечетной
            rowValDelta *= -1;                                      //Сменить изменение следующего элемента
        }
        /*Вывод массива в читаемом виде*/
        System.out.println("Состояние массива:");
        for (int i = 0; i < arrayLengthX; i++){
            System.out.print("[");
            for (int j = 0; j < arrayLengthY - 1; j++){
                //В ТЗ указано "на вывод каждого числа ровно 3 символа". Начиная с элемента №101 цифры слипаются
                System.out.printf("%3d", snakeArray[i][j]);
            }
            System.out.printf("%3d]\r\n", snakeArray[i][arrayLengthY - 1]);
        }
        System.out.println();
    }
}
