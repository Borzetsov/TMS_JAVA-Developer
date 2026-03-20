/**
 * Домашнее задание №1
 * @author Aleksei Borzetsov
 * 26.02.2026
 */

public class HomeWork1 {
    public void run(){
/*
1. Задание состоит из пяти обязательных и одной дополнительной задачи.
2. Все задачи выполняются последовательно.
3. По каждой задаче составляется отчет.
4. Отчеты выводятся в консоль.
 */
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №1 | Выдано 25.02.2026 ;");
        System.out.println();

        /*Задача 1*/
        //Входные данные
        float b = 1f;
        float c = 0f;
        //Вычисления
        float a = 4 * (b + c - 1) / 2;
        //float a = 2 * (b + c - 1);    //Альтернатива
        //Отчет
        System.out.println("Задача №1");
        System.out.println("Входные данные: b = " + b + ", c = " + c);
        System.out.println("Результат: 4 * (b + c - 1) / 2 = " + a);
        System.out.println();

        /*Задача 2*/
        //Входные данные
        int n = 10;
        //Вычисления
        int initialValue = n;
        //Взять модуль
        initialValue = (initialValue < 0) ? initialValue * (-1) : initialValue;
        //Определить разряд 0
        int digit0 = initialValue % 10;
        //Определить разряд 1
        int digit1 = initialValue / 10;
        //Вычислить сумму
        int result = digit0 + digit1;
        //Отчет
        System.out.println("Задача №2");
        System.out.println("Входные данные: " + n);
        System.out.println("Результат: сумма цифр = " + result);
        System.out.println();

        /*Задача 3*/
        //Входные данные
        n = 201;
        //Вычисления
        initialValue = n;
        //Взять модуль
        initialValue = (initialValue < 0) ? initialValue * (-1) : initialValue;
        //Определить разряд 0
        digit0 = initialValue % 10;
        //Определить разряд 1
        initialValue = initialValue / 10;
        digit1 = initialValue % 10;
        //Определить разряд 2
        int digit2 = initialValue / 10;
        //Вычислить сумму
        result = digit0 + digit1 + digit2;
        //Отчет
        System.out.println("Задача №3");
        System.out.println("Входные данные: " + n);
        System.out.println("Результат: сумма цифр = " + result);
        System.out.println();

        /*Задача 4*/
        //Входные данные
        //Для float: 7-й знак после точки может не соответствовать аргументу (требуется double)
        float nf = -0.599999f;
        //Вычисления
        //Определить разряд -0
        int digitNeg0 = (int)(nf * 10) % 10;
        //Взять модуль разряда -0
        digitNeg0 = (digitNeg0 < 0) ? digitNeg0 * (-1) : digitNeg0;
        //Определить требуемое изменение целой части
        int changeVal = (digitNeg0 > 4) ? 1 : 0;
        //Определит целую часть без округления
        result = (int)nf;
        //Округлить целую часть
        result = (nf < 0) ? (result - changeVal) : (result + changeVal);
        //Отчет
        System.out.println("Задача №4");
        System.out.println("Входные данные: n = " + (int)nf);
        System.out.println("Результат округления: " + result);
        System.out.println();

        /*Задача 5*/
        //Входные данные
        int q = 21;
        int w = 8;
        //Проверка входных данных: на нуль делить нельзя
        float wf = (float)w;
        //Если w == 0, делить на малое число
        //В таком случае целая часть будет максимальным целым, что намекнет на ошибку
        wf = (w == 0) ? 0.000000001f : wf;
        //Вычисления
        //Определить целую часть
        result = (int)(q / wf);
        //Определить остаток
        int spare = q - (result * w);
        //Взять модуль остатка
        spare = (spare < 0) ? spare * (-1) : spare;
        //Уточнить остаток
        spare = (w == 0) ? 0 : spare;
        //Отчет
        System.out.println("Задача №5");
        System.out.println("Входные данные: q = " + q + ", w = " + w);
        System.out.println("Результат: q / w = " + result + " и в остатке " + spare);
        System.out.println();

        /*Задача **/
        //Пришлось расщепить генерацию отчета
        //Входные данные
        int ai = 1;
        int bi = 2;
        //Отчет начало
        System.out.println("Задача №*");
        System.out.println("Входные данные: a = " + ai + ", b = " + bi);
        //Вычисления
        ai = ai + bi;
        bi = ai - bi;       //bi = ai + bi - bi = ai
        ai = ai - bi;       //ai = ai + bi - ai = bi
        //Отчет конец
        System.out.println("Результат: a = " + ai + ", b = " + bi);
        System.out.println();
    }
}
