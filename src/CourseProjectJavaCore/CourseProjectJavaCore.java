/**
 * Classname    CourceProjectJavaCore
 * @version     0.06
 * @author      Aleksei Borzetsov
 * date         05.05.2026
 */
 
package CourseProjectJavaCore;

import CourseProjectJavaCore.exceptions.IllegalValueException;
import CourseProjectJavaCore.model.Account;
import CourseProjectJavaCore.model.PaymentOrder;
import CourseProjectJavaCore.model.TransactionProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public final class CourseProjectJavaCore {

    public void run() {
        System.out.println("Курсовой проект №1 Java Core");
        System.out.println();

        //Создание списка счетов
        ArrayList<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            accounts.add(new Account());
        }
        //Пополнение баланса
        for (Account currentAccount : accounts) {
            try {
                currentAccount.income((long) (Math.random() * 2000L));
            } catch (IllegalValueException e) {

            }
        }
        //Запись в файл
        Path accountFilePath = Path.of(Account.DEFAULT_PATH);
        //Создание директории
        if (Files.notExists(accountFilePath)) {
            try {
                Files.createDirectories(accountFilePath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        //Создание файла
        Path accountFile = accountFilePath.resolve(Account.FILE_NAME + Account.FILE_EXTENSION);
        try {
            Files.writeString(accountFile, "", StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            for (Account currentAccount : accounts)
                Files.writeString(accountFile, currentAccount.toString() + "\r\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //Создание платежных поручений
        ArrayList<PaymentOrder> paymentOrders = new ArrayList<>();
        paymentOrders.add(new PaymentOrder(
                /*Счет плательщика*/
                accounts.get((int)(Math.random() * (accounts.size() - 1))).getNumber(),
                /*Счет получателя*/
                "99999-99999",
                /*Сумма платежа*/
                (int)(Math.random() * 2000)));
        for (int i = 1; i < 9; i++) {
            paymentOrders.add(new PaymentOrder(
                    /*Счет плательщика*/
                    accounts.get((int)(Math.random() * (accounts.size() - 1))).getNumber(),
                    /*Счет получателя*/
                    accounts.get((int)(Math.random() * (accounts.size() - 1))).getNumber(),
                    /*Сумма платежа*/
                    (int)(Math.random() * 2000)));
        }
        paymentOrders.add(new PaymentOrder(
                /*Счет плательщика*/
                "00000-00000",
                /*Счет получателя*/
                accounts.get((int)(Math.random() * (accounts.size() - 1))).getNumber(),
                /*Сумма платежа*/
                (int)(Math.random() * 2000)));
        //Запись в файл
        Path paymentOrderFilePath = Path.of(PaymentOrder.DEFAULT_PATH);
        /*Создание директории*/
        if (Files.notExists(paymentOrderFilePath)) {
            try {
                Files.createDirectories(paymentOrderFilePath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        int i = 0;
        for (PaymentOrder currentPaymentOrder : paymentOrders) {
            //Создание файла
            i++;
            Path paymentOrderFile = paymentOrderFilePath.resolve("paymentOrder" + i
                    + PaymentOrder.FILE_EXTENSION);
            try {
                Files.writeString(paymentOrderFile, "", StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
                Files.writeString(paymentOrderFile, currentPaymentOrder.toString(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        i++;
        Path paymentOrderFile = paymentOrderFilePath.resolve("someFile" + i
                + ".csv");
        try {
            Files.writeString(paymentOrderFile, "", StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            Files.writeString(paymentOrderFile, "Some Content", StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //Обработчик транзакций
        TransactionProcessor transactionProcessor = TransactionProcessor.getINSTANCE();
        //Организация пользовательского интерфейса
        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("Для вызова операции парсинга файлов введите 1");
        System.out.println("Для вызова операции вывода списка всех переводов из файла-отчета введите 2");
        System.out.println("Для выхода введите 0");
        int userValue = -1;
        do {
            if (consoleScanner.hasNextLine()) {
                if (consoleScanner.hasNextInt()) {
                    userValue = consoleScanner.nextInt();
                    consoleScanner.nextLine();
                    if (userValue == 1) {
                        System.out.println("Выполняется операции парсинга файлов");
                        transactionProcessor.executePaymentOrders();
                        System.out.println("Готово");
                    } else if (userValue == 2) {
                        System.out.println("Выполняется вывод списка всех переводов из файла-отчета");
                        transactionProcessor.generateReport();
                        System.out.println("Готово");
                    }
                }
            }
        } while (userValue != 0);
        System.out.println("Завершение работы");
    }
}
