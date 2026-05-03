/**
 * Classname    Account
 * @version     0.04
 * @author      Aleksei Borzetsov
 * date         03.05.2026
 */

package CourseProjectJavaCore.model;

/**
 * Класс счета
 */
public final class Account {
    public static final String DEFAULT_PATH = "src\\CourseProjectJavaCore\\dataChannels\\";

    private static final long DEFAULT_INITIAL_BALANCE = 0L;

    public static final String REGEXP = "Account: [0-9]{5}-[0-9]{5} Balance: [0-9]+\\b";
    public static final String FILE_NAME = "accounts";
    public static final String DATABASE_FILE_EXTENSION = ".txt";

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
     * @return -- статус операции
     */
    public BalanceChangeStatus income(long value) {
        if (value <= 0) return BalanceChangeStatus.BALANCE_CHANGE_STATUS_ILLEGAL_VALUE;
        balance += value;
        return BalanceChangeStatus.BALANCE_CHANGE_STATUS_OK;
    }

    /**
     * Уменьшить баланс
     * @param value -- величина списания
     * @return -- статус операции
     */
    public BalanceChangeStatus expense(long value) {
        if (value <= 0) return BalanceChangeStatus.BALANCE_CHANGE_STATUS_ILLEGAL_VALUE;
        if ((this.balance - value) < 0) return BalanceChangeStatus.BALANCE_CHANGE_STATUS_INSUFFICIENT_FUNDS;
        this.balance -= value;
        return BalanceChangeStatus.BALANCE_CHANGE_STATUS_OK;
    }

    /**
     * Возможно ли списание со счета
     * @param value -- запрашиваемая сумма
     * @return -- статус
     */
    public boolean isExpensePossible(long value) {
        return this.balance >= value;
    }

    public String getNumber() {
        return this.number;
    }

    @Override
    public String toString() {
        return "Account: " + this.number + " Balance: " + this.balance;
    }
}
