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
        Point2D[] tmpPoints = new Point2D[3];
        tmpPoints[0] = pointA;
        tmpPoints[1] = pointB;
        tmpPoints[2] = pointC;
        super.setPoints(tmpPoints);
    }

    /**
     * Вычисляет площадь треугольника по формуле Герона
     * @return  площадь треугольника
     */
    @Override
    public double countArea() {
        Point2D[] tmpPoints = super.getPoints();
        //Определить длины сторон
        double sideA = tmpPoints[0].distance((tmpPoints[1]));
        double sideB = tmpPoints[1].distance((tmpPoints[2]));
        double sideC = tmpPoints[2].distance((tmpPoints[0]));
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
        Point2D[] tmpPoints = super.getPoints();
        double perimeter = tmpPoints[0].distance(tmpPoints[1]);
        perimeter += tmpPoints[1].distance(tmpPoints[2]);
        perimeter += tmpPoints[2].distance(tmpPoints[0]);
        return perimeter;
    }
}
