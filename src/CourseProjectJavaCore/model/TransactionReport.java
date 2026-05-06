/**
 * Classname    TransactionReport
 * @version     0.06
 * @author      Aleksei Borzetsov
 * date         06.05.2026
 */

package CourseProjectJavaCore.model;

import java.time.ZonedDateTime;

/**
 * Класс отчета по транзакции
 */
public final class TransactionReport {
    public static final String DEFAULT_PATH = "src\\CourseProjectJavaCore\\dataChannels\\";
    public static final String FILE_NAME = "report";
    public static final String FILE_EXTENSION = ".txt";

    private final ZonedDateTime reportTime;
    private final String paymentOrderFileName;
    private String matter;
    private String result;

    public TransactionReport(ZonedDateTime time, String paymentOrderFileName, String matter, String result) {
        this.reportTime = time;
        this.paymentOrderFileName = paymentOrderFileName;
        this.matter = matter;
        this.result = result;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return this.reportTime + "\t" +
                this.paymentOrderFileName + "\t" +
                this.matter + "\t" +
                this.result;
    }
}
