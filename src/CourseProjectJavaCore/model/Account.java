/**
 * Classname    Account
 * @version     0.06
 * @author      Aleksei Borzetsov
 * date         05.05.2026
 */

package CourseProjectJavaCore.model;

import CourseProjectJavaCore.exceptions.IllegalValueException;
import CourseProjectJavaCore.exceptions.InsufficientFundsException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Класс счета
 */
public final class Account {
    public static final String DEFAULT_PATH = "src\\CourseProjectJavaCore\\dataChannels\\";

    private static final long DEFAULT_INITIAL_BALANCE = 0L;

    public static final String REGEXP = "Account: [0-9]{5}-[0-9]{5} Balance: [0-9]+\\b";

    public static final String FILE_NAME = "accounts";
    public static final String FILE_EXTENSION = ".txt";

    private final String number;
    private long balance;

    /**
     * Создать новый счет
     * Номер вида ххххх-ххххх
     * генерируется автоматически
     */
    public Account() {
        String randomNumber = String.valueOf((int)(Math.random() * 89999));
        StringBuilder accountNumberTemplate = new StringBuilder("00000-00000");
        accountNumberTemplate.replace(5 - randomNumber.length(), 5, randomNumber);
        randomNumber = String.valueOf((int)(Math.random() * 89999));
        accountNumberTemplate.replace(11 - randomNumber.length(), 11, randomNumber);
        this.number = accountNumberTemplate.toString();
        this.balance = DEFAULT_INITIAL_BALANCE;
    }

    /**
     * Создает аккаунт по записи из базы данных
     * Проверка записи происходит до вызова конструктора
     * @param record -- запись
     */
    public Account(String record) {
        String[] recordComponents = record.split(" ");
        this.number = recordComponents[1];
        this.balance = Integer.parseInt(recordComponents[3]);
    }

    /**
     * Пополнить баланс
     * @param value -- величина пополнения
     * @throws IllegalValueException -- неверная сумма
     */
    public void income(long value) throws IllegalValueException {
        if (value <= 0) throw new IllegalValueException("Illegal value");
        balance += value;
    }

    /**
     * Уменьшить баланс
     * @param value -- величина списания
     * @throws InsufficientFundsException -- нехватка средств
     * @throws IllegalValueException -- неверная сумма
     */
    public void expense(long value) throws InsufficientFundsException, IllegalValueException {
        if (value <= 0) throw new IllegalValueException("Illegal value");
        if ((this.balance - value) < 0) throw new InsufficientFundsException("Insufficient funds");
        this.balance -= value;
    }

    public String getNumber() {
        return this.number;
    }

    @Override
    public String toString() {
        return "Account: " + this.number + " Balance: " + this.balance;
    }

    public static boolean dataBaseConnect(Path path) throws IOException {
        if (!Files.exists(path)) throw new IOException("Accounts data base not found");
        return true;
    }
}
