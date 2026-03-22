/**
 * Classname    Shape
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         22.03.2026
 */
 
package HomeWork7;

import java.awt.geom.Point2D;

/**
 * Абстрактный класс фигур
 */
public abstract class Shape {

    //У всех фигур есть некоторый набор точек
    public Point2D[] points;

    /**
     * Вычисляет площадь
     * @return  площадь
     */
    public abstract double countArea();         //Найти площадь

    /**
     * Вычисляет периметр
     * @return  периметр
     */
    public abstract double countPerimeter();    //Найти периметр
}
