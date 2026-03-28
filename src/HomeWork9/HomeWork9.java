/**
 * Classname    HomeWork9
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         28.03.2026
 */
 
package HomeWork9;

public class HomeWork9 {

    public void run() {

        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №9 | Выдано 25.03.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        System.out.println();

        Rabbit fluffy = new Rabbit("Fluffy");
        fluffy.voice();
        fluffy.eat("Grass");
        System.out.println();

        //Единственная собака
        Dog chappy = new Dog("Chappy");
        chappy.voice();
        chappy.eat("Dog food");
        System.out.println();

        Tiger scherKhan = new Tiger("Scher Khan");
        scherKhan.voice();
        scherKhan.eat("Grass");
        System.out.println();

        System.out.println("Задача *");
        System.out.println();

        //Единственная собака
        SingleDog tooziq = SingleDog.getINSTANCE();
        //Дать кличку
        tooziq.setName("Тузик");
        //Та же собака
        SingleDog tyz = SingleDog.getINSTANCE();
        //Дать другую кличку
        tyz.setName("Шарик");
        //В итоге, 2 переменные указывают на один единственный объект
        tooziq.eat("Dog food");
        tyz.eat("Dog food");
    }
}
