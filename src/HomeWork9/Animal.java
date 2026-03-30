/**
 * Classname    Animal
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         28.03.2026
 */
 
package HomeWork9;

public abstract class Animal {

    public void voice() {
        System.out.println("Животное не издает звуков");
    }

    public void eat(String food) {
        System.out.println("Рацион неизвестен");
    }

    public String getAnimalSpecies() {
        return "Неизвестное животное";
    }
}
