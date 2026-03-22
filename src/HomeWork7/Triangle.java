/**
 * Classname    Triangle
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         22.03.2026
 */
 
package HomeWork7;

import java.awt.geom.Point2D;

/**
 * Класс описывает треугольник
 */
public class Triangle extends Shape {

    /**
     * Конструктор объекта типа "Треугольник"
     * @param pointA    первая точка
     * @param pointB    вторая точка
     * @param pointC    третья точка
     */
    public Triangle(Point2D pointA, Point2D pointB, Point2D pointC) {
        super.points = new Point2D[3];
        super.points[0] = pointA;
        super.points[1] = pointB;
        super.points[2] = pointC;
    }

    /**
     * Вычисляет площадь треугольника по формуле Герона
     * @return  площадь треугольника
     */
    @Override
    public double countArea() {
        //Определить длины сторон
        double sideA = super.points[0].distance((super.points[1]));
        double sideB = super.points[1].distance((super.points[2]));
        double sideC = super.points[2].distance((super.points[0]));
        //Формула Герона
        double p = (sideA + sideB + sideC) / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    /**
     * Вычисляет периметр треугольника
     * @return  периметр треугольника
     */
    @Override
    public double countPerimeter() {
        double perimeter = super.points[0].distance(super.points[1]);
        perimeter += super.points[1].distance(super.points[2]);
        perimeter += super.points[2].distance(super.points[0]);
        return perimeter;
    }
}
