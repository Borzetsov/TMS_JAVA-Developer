/**
 * Classname    HomeWork13
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         10.04.2026
 */
 
package HomeWork13;

import java.io.IOError;
import java.io.IOException;
import java.util.Scanner;

public class HomeWork13 {

    public void run() {
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №13 | Выдано 08.04.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        System.out.println();

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Введите логин:");
        String userLogin = consoleScanner.nextLine();
        System.out.println("Введите пароль:");
        String userPassword = consoleScanner.nextLine();
        System.out.println("Подтвердите пароль:");
        String userConfirmPassword = consoleScanner.nextLine();

        if (!Authorizer.tryAuthorize(userLogin, userPassword, userConfirmPassword)) {
            System.out.println("Ошибка авторизации");
        }
        else {
            System.out.println("Авторизация пройдена");
        }
        System.out.println();

        System.out.println("Задача *");
        System.out.println();

        try {
            System.out.println("Вход в блок try");
            throw new IOException();
        } catch (IOException e) {
            System.out.println("Вход в блок catch");
            System.exit(0);  //Тупо-натупо прерываем прогу
        } finally {
            System.out.println("Вход в блок finally");
        }
    }
}
