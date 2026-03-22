/**
 * Classname    HomeWork7
 *
 * @version 0.01
 * @author Aleksei Borzetsov
 * date         22.03.2026
 */

package HomeWork7;

import java.awt.geom.Point2D;

public class HomeWork7 {

    public void run() {

        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №7 | Выдано 18.03.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        System.out.println();

        CEO person1 = new CEO();
        Accountant person2 = new Accountant();
        Laborer person3 = new Laborer();

        Printable[] stuff = {person1, person2, person3};
        for (Printable currentPerson : stuff) {
            currentPerson.printInfo();
        }
        System.out.println();

        System.out.println("Задача 2");
        System.out.println();

        Triangle triangle1 = new Triangle(new Point2D.Double(0, 0), new Point2D.Double(5, 0),
                                          new Point2D.Double(5, 5));

        Triangle triangle2 = new Triangle(new Point2D.Double(0, 0), new Point2D.Double(10, 0),
                                          new Point2D.Double(10, 10));

        Rectangle rectangle1 = new Rectangle(new Point2D.Double(0, 0), 10, 10);
        Rectangle rectangle2 = new Rectangle(new Point2D.Double(0, 0), 20, 10);

        Circle circle1 = new Circle(new Point2D.Double(0, 0), 25);

        Shape[] shapes = {triangle1, triangle2, rectangle1, rectangle2, circle1};

        double totalArea = 0;
        for (Shape currentShape : shapes) totalArea += currentShape.countArea();
        System.out.printf("Сумма площадей фигур: \t\t%.3f", totalArea);
        System.out.println();

        double totalPerimeter = 0;
        for (Shape currentShape : shapes) totalPerimeter += currentShape.countPerimeter();
        System.out.printf("Сумма периметров фигур: \t%.3f", totalPerimeter);
        System.out.println();
        System.out.println();
    }
}
