/**
 * Classname    User
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         31.03.2026
 */
 
package HomeWork10;

public class User implements Cloneable{

    private static int counterID = 0;

    private Person person;
    private int ID;

    public User() {}

    public User(Person person) {
        this.person = person;
        this.ID = counterID;
        counterID++;
    }

    public int getID() {
        return this.ID;
    }

    public Person getPerson() {
        return this.person;
    }

    public User getClone(User[] users, int ID, boolean deepClone) throws CloneNotSupportedException {
        if (ID >= users.length) return null;
        System.out.print("Клонируем пользователя " + ID);
        User cloneUser = new User();
        if (deepClone) {
            System.out.println(" (Deep)");
            cloneUser.ID = users[ID].getID();
            cloneUser.person = this.person.clone();
        }
        else {
            System.out.println(" (Shallow)");
            cloneUser = users[ID].clone();
        }
        return cloneUser;
    }

    @Override
    public String toString() {
        return "Пользователь:\t" + this.ID + " " + this.person;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}
