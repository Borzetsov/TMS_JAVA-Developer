/**
 * Classname    ApplePainter
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         28.03.2026
 */
 
package HomeWork8;

import java.lang.reflect.Field;

public class ApplePainter {

    public void paintApple (Apple apple) {
        System.out.println("Красим яблоко в зеленый...");
        //Получить массив полей класса
        Field[] fields = apple.getClass().getDeclaredFields();
        //Для всех полей String color
        for (Field field : fields) {
            if ((field.getType().equals(java.lang.String.class)) && (field.getName().equals("color"))) {
                //Разрешить доступ
                field.setAccessible(true);
                try {
                    //Изменить цвет
                    field.set(apple, "Green");
                    //Запретить доступ
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    System.out.println("Это яблоко не покрасить");
                    throw new RuntimeException(e);
                }
            }
            else {
                System.out.println("Это яблоко не имеет цвета О_о");
            }
        }
    }
}
