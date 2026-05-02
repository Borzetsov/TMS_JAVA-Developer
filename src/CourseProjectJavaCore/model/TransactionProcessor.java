/**
 * Classname    TransactionProcessor
 * @version     0.02
 * @author      Aleksei Borzetsov
 * date         02.05.2026
 */

package CourseProjectJavaCore.model;

import java.util.ArrayList;

public class TransactionProcessor {

    private static final String ACCOUNTS_DEFAULT_PATH =
            "src\\CourseProjectJavaCore\\dataChannels\\";
    private static final String INPUT_DEFAULT_PATH =
            "src\\CourseProjectJavaCore\\dataChannels\\input\\";
    private static final String ARCHIVE_DEFAULT_PATH =
            "src\\CourseProjectJavaCore\\dataChannels\\archive\\";

    private ArrayList<TransactionReport> report = new ArrayList<>();

    public TransactionProcessor() {

    }

    public void executePaymentOrders() {
        /*
        1. Получить список файлов.
        2. Если файл имеет расширение .txt, добавить в список платежных поручений.
        3. Выполнить платежные поручения:
            3.1. Выполнить.
            3.2. Добавить в файл статус исполнения.
            3.3. Добавить в список отчет.
            3.4. Переместить файл в архив.
        */
    }

    public void generateReport() {

    }

    private Account loadAccount(String accountNumber) {
        //Найти аккаунт в базе данных
        return new Account();
    }

    private boolean updateAccount(Account account) {
        //Обновить запись в базе данных
        return true;
    }
}
