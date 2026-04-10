/**
 * Classname    WrongPasswordException
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         10.04.2026
 */
 
package HomeWork13;

public class WrongPasswordException extends Exception {

    public WrongPasswordException() {
        super();
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
