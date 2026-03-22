/**
 * Classname    Laborer
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         22.03.2026
 */
 
package HomeWork7;

public class Laborer implements Printable {

    private final String jobTitle = "Laborer";

    @Override
    public void printInfo() {
        System.out.println(this.jobTitle);
    }
}
