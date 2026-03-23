/**
 * Classname    Rectangle
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         22.03.2026
 */
 
package HomeWork7;

import java.awt.geom.Point2D;

/**
 * Класс описывает прямоугольник
 */
public class Rectangle extends Shape {

    private double width;       //ширина
    private double height;      //высота

    /**
     * Конструктор объекта типа "Прямоугольник"
     * @param point     вершина прямоугольника
     * @param width     ширина
     * @param height    высота
     */
    public Rectangle(Point2D point, double width, double height) {
        Point2D[] tmpPoints = new Point2D[1];
        tmpPoints = new Point2D[1];
        tmpPoints[0] = point;
        super.setPoints(tmpPoints);
        this.width = width;
        this.height = height;
    }

    /**
     * Вычисляет площадь прямоугольника
     * @return  площадь прямоугольника
     */
    @Override
    public double countArea() {
        return this.width * this.height;
    }

    /**
     * Вычисляет периметр прямоугольника
     * @return  периметр прямоугольника
     */
    @Override
    public double countPerimeter() {
        return 2 * (this.width + this.height);
    }
}
