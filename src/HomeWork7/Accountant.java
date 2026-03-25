/**
 * Classname    Accountant
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         22.03.2026
 */
 
package HomeWork7;

public class Accountant implements Printable {

    private final String jobTitle = "Accountant";

    @Override
    public void printInfo() {
        System.out.println(this.jobTitle);
    }
}
