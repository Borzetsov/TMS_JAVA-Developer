/**
 * Classname    Rabbit
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         28.03.2026
 */
 
package HomeWork9;

public class Rabbit extends HerbivoreAnimal {

    private String name;

    private String[] diet = {"Grass", "Carrot", "Cabbage"};

    public Rabbit(String name) {
        this.name = name;
    }

    @Override
    public String getAnimalSpecies() {
        return "Кролик";
    }

    @Override
    public void voice() {
        System.out.println("Кролик сопит");
    }

    @Override
    public void eat(String food) {
        System.out.println("Предложили " + this.name + " " + food + ".");
        for (String checkFood : this.diet) {
            if (checkFood.equalsIgnoreCase(food)) {
                System.out.println(this.getAnimalSpecies() + " по кличке " + this.name +
                                   " любит есть " + food + ".");
                return;
            }
        }
        System.out.println(this.getAnimalSpecies() + " по кличке " + this.name +
                           " недоволен и отказывается это есть.");
    }

    public String getName() {
        return this.name;
    }
}
