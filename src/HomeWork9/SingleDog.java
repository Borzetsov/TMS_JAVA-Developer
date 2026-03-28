/**
 * Classname    SingleDog
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         28.03.2026
 */
 
package HomeWork9;

public class SingleDog extends PredatoryAnimal {

    //Паттерн Singleton
    private static SingleDog INSTANCE = new SingleDog();

    private String name;

    private String[] diet = {"Meat", "Dog food"};

    //Конструктор вызывается один раз при инициализации класса
    private SingleDog() {

    }

    //Запрос доступа к единственному экземпляру
    public static SingleDog getINSTANCE() {
        return INSTANCE;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAnimalSpecies() {
        return "Собака";
    }

    @Override
    public void voice() {
        System.out.println("Собака лает");
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
