package HomeWork6;

import java.util.Scanner;

/**
 * Домашнее задание №6
 * @author Aleksei Borzetsov
 * 03/20/26
 */
public class HomeWork6 {
    public void run(){
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №6 | Выдано 16.03.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        System.out.println();

        //Создать 3 карты
        CreditCard cc1 = new CreditCard(100);
        CreditCard cc2 = new CreditCard(200);
        CreditCard cc3 = new CreditCard();
        //Пополнить баланс
        cc1.addMoney(100);
        cc2.addMoney(1000);
        //Совершить перевод
        long borrow = cc2.takeMoney(500);
        cc3.addMoney(borrow);
        //Вывести информацию по счетам
        cc1.getInfo();
        cc2.getInfo();
        cc3.getInfo();

        System.out.println("Задача *");
        System.out.println();
        //Установка банкомата
        MyATM fairyATM = new MyATM(10, 0, 0);
        //Загрузка купюр
        fairyATM.addPaper(1, 1, 1);
        //
        Scanner atmScanner = new Scanner(System.in);
        do {
            fairyATM.getInfo();
            System.out.println("Введите сумму для снятия");
            if (atmScanner.hasNextInt()){
                if (!fairyATM.takeMoney(atmScanner.nextInt())) System.out.println("Отклонено\r\n");
                atmScanner.nextLine();
            }
        } while(fairyATM.havePaper());
        System.out.println("Средства в банкомате закончились");
    }
}
