/**
 * Classname    AccountNotExistException
 * @version     0.02
 * @author      Aleksei Borzetsov
 * date         06.05.2026
 */

package CourseProjectJavaCore.exceptions;

public class AccountNotFoundException extends Exception {
  public AccountNotFoundException(String message) {
    super(message);
  }
}
