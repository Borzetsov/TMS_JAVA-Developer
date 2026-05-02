/**
 * Classname    TransactionReport
 * @version     0.02
 * @author      Aleksei Borzetsov
 * date         02.05.2026
 */

package CourseProjectJavaCore.model;

import java.time.ZonedDateTime;

/**
 * Класс отчета по транзакции
 */
public class TransactionReport {
    public static final String DEFAULT_PATH = "src\\CourseProjectJavaCore\\dataChannels\\archive\\";
    public static final String TRANSACTION_REPORT_FILE_EXTENSION = ".txt";

    private final ZonedDateTime reportTime;
    private final String paymentOrderFileName;
    private final String matter;
    private final String result;

    public TransactionReport(String paymentOrderFileName, String matter, String result) {
        this.reportTime = ZonedDateTime.now();
        this.paymentOrderFileName = paymentOrderFileName;
        this.matter = matter;
        this.result = result;
    }

    @Override
    public String toString() {
        return this.reportTime + " " +
                this.paymentOrderFileName + " " +
                this.matter + " " +
                this.result + "\r\n";
    }
}
