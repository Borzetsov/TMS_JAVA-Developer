/**
 * Classname    CEO
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         22.03.2026
 */
 
package HomeWork7;

public class CEO implements Printable {

    private final String jobTitle = "CEO";

    @Override
    public void printInfo() {
        System.out.println(this.jobTitle);
    }
}
