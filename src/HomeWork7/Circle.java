/**
 * Classname    Circle
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         22.03.2026
 */
 
package HomeWork7;

import java.awt.geom.Point2D;

/**
 * Класс описывает окружность
 */
public class Circle extends Shape {

    //Радиус окружности
    private double radius;

    /**
     * Конструктор объекта типа "Окружность"
     * @param point     точка центра окружности
     * @param radius    радиус окружности
     */
    public Circle(Point2D point, double radius) {
        super.points = new Point2D[1];
        super.points[0] = point;
        this.radius = radius;
    }

    /**
     * Вычисляет площадь круга
     * @return  площадь круга
     */
    @Override
    public double countArea() {
        return Math.PI * this.radius * this.radius;
    }

    /**
     * Вычисляет длину окружности
     * @return  длина окружности
     */
    @Override
    public double countPerimeter() {
        return 2 * Math.PI * this.radius;
    }
}
