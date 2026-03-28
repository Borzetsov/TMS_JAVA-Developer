/**
 * Classname    Tiger
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         28.03.2026
 */
 
package HomeWork9;

public class Tiger extends PredatoryAnimal {

    private String name;

    private String[] diet = {"Meat"};

    public Tiger(String name) {
        this.name = name;
    }

    @Override
    public String getAnimalSpecies() {
        return "Тигр";
    }

    @Override
    public void voice() {
        System.out.println("Тигр рычит");
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
