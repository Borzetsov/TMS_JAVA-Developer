/**
 * Classname    PaymentOrder
 * @version     0.02
 * @author      Aleksei Borzetsov
 * date         28.04.2026
 */

package CourseProjectJavaCore.model;

import java.time.ZonedDateTime;

/**
 * Класс платежного поручения
 */
public class PaymentOrder {

    public static final String PAYMENT_ORDER_REGEXP =
            "^Payer: [0-9]{5}-[0-9]{5} Recipient: [0-9]{5}-[0-9]{5} Value: [0-9]+\\b";

    public static final String PAYMENT_ORDER_FILE_EXTENSION = ".txt";

    private long number;
    private final String payerAccountNumber;
    private final String recipientAccountNumber;
    private final long paymentValue;

    private ZonedDateTime created;
    private ZonedDateTime processed;
    private PaymentOrderProcessStatus processStatus;

    public PaymentOrder(String payerNumber, String recipientNumber, long value) {
        this.payerAccountNumber = payerNumber;
        this.recipientAccountNumber = recipientNumber;
        this.paymentValue = value;
    }

    public PaymentOrder(String record) {
        String[] splitRecord = record.split(" ");
        this.payerAccountNumber = splitRecord[1];
        this.recipientAccountNumber = splitRecord[3];
        this.paymentValue = Long.parseLong(splitRecord[5]);
    }

    public PaymentOrderProcessStatus process() {
        return PaymentOrderProcessStatus.PAYMENT_ORDER_PROCESS_STATUS_OK;
    }

    @Override
    public String toString() {
        return "Payer: " + this.payerAccountNumber
                + " Recipient: " + this.recipientAccountNumber
                + " Value: " + this.paymentValue;
    }
}
