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
    private Point2D[] points;

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

    /**
     * Задать точки фигуры
     * @param points    массив точек фигуры
     */
    public void setPoints(Point2D[] points) {
        this.points = points;
    }

    /**
     * Получить массив точек фигуры
     * @return  массив точек фигуры
     */
    public Point2D[] getPoints() {
        return this.points;
    }
}
