/**
 * Classname    Person
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         31.03.2026
 */
 
package HomeWork10;

public class Person implements Cloneable{

    private Name name;

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name.getFirstName() + " " + this.name.getMiddleName() + " " +
               this.name.getLastName();
    }

    @Override
    public Person clone() throws CloneNotSupportedException{
        return (Person) super.clone();
    }
}
