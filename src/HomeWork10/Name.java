/**
 * Classname    Name
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         31.03.2026
 */
 
package HomeWork10;

public class Name {

    private String firstName;
    private String middleName;
    private String lastName;

    public Name(String firstName, String  middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }
}
