/**
 * Classname    Animals
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         17.04.2026
 */
 
package HomeWork15;

import java.util.LinkedList;

public class Animals {
    private LinkedList<String> animalNames = new LinkedList<>();

    public void addAnimal(String newAnimal) {
        this.animalNames.addFirst(newAnimal);
    }

    public void removeAnimal() {
        this.animalNames.removeLast();
    }

    @Override
    public String toString() {
        return animalNames.toString();
    }
}
