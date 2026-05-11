/**
 * Classname    DataBaseConnectionException
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         05.05.2026
 */

package CourseProjectJavaCore.exceptions;

import java.io.IOException;

public class DataBaseConnectionException extends IOException {
  public DataBaseConnectionException(String message) {
    super(message);
  }
}
