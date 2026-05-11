/**
 * Classname    HomeWork15
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         17.04.2026
 */
 
package HomeWork15;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeWork15 {

    public void run() {
        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №15 | Выдано 15.04.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        System.out.println();

        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("Введите набор чисел");
        String userInput = consoleScanner.nextLine();
        /*Получить список уникальных чисел*/
        Set<Integer> userInputIntegerSet = new HashSet<>();
        Pattern intPattern = Pattern.compile("\\b[0-9]+\\b");
        Matcher intMatcher = intPattern.matcher(userInput);
        while (intMatcher.find()) {
            userInputIntegerSet.add(Integer
                    .valueOf(userInput.substring(intMatcher.start(), intMatcher.end())));
        }
        if (!userInputIntegerSet.isEmpty()) {
            System.out.println("Коллекция уникальных чисел:");
            System.out.println(userInputIntegerSet);
        }
        else {
            System.out.println("Чисел не обнаружено");
        }
        System.out.println();

        System.out.println("Задача 2");
        System.out.println();

        Animals animals = new Animals();
        animals.addAnimal("Собака");
        animals.addAnimal("Кошка");
        animals.addAnimal("Медведь");
        animals.addAnimal("Лошадь");
        System.out.println(animals);
        animals.removeAnimal();
        animals.addAnimal("Собака");
        System.out.println(animals);
        System.out.println();

        System.out.println("Задача 3");
        System.out.println();

        /*Сформировать список студентов 1-го курса*/
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Александров",     "1255"));
        students.add(new Student("Белоногова",      "1255"));
        students.add(new Student("Сидоров",         "1255"));
        students.add(new Student("Корнелевский",    "1255"));
        students.add(new Student("Ланг",            "1255"));
        students.add(new Student("Михайлов",        "1255"));
        students.add(new Student("Никитин",         "1255"));
        students.add(new Student("Попов",           "1255"));
        students.add(new Student("Тимофеев",        "1255"));
        students.add(new Student("Юлина",           "1255"));
        students.add(new Student("Арсёнов",     "1215"));
        students.add(new Student("Жарков",      "1215"));
        students.add(new Student("Забелин",     "1215"));
        students.add(new Student("Косогоров",   "1215"));
        students.add(new Student("Куряев",      "1215"));
        students.add(new Student("Махалев",     "1215"));
        students.add(new Student("Перловский",  "1215"));
        students.add(new Student("Синяков",     "1215"));
        students.add(new Student("Шиляев",      "1215"));
        students.add(new Student("Юрков",       "1215"));

        Student.printStudents(students, 1);

        /*Поставить 40 оценок по каждому предмету*/
        for (int i = 0; i < Courses.values().length; i++) {
            for (Student currentStudent : students) {
                for (int j = 0; j < 40; j++) {
                    currentStudent.setMark(Courses.values()[i], (int)(1.56 + Math.random() * 4));
                }
            }
        }
        /*Весенний призыв*/
        Student.session(students);
        Student.printStudents(students, 1);
        Student.printStudents(students, 2);

        System.out.println("Задача *");
        System.out.println();

        System.out.println("Демонстрация работы MyArrayList");
        System.out.println();
        System.out.println("Создан список Integer[20]");
        MyArrayList<Integer> customIntegerList = new MyArrayList<Integer>();
        for (int i = 0; i < 20; i++) customIntegerList.add(i);
        System.out.println("Элементов: " + customIntegerList.getLength());
        System.out.println(customIntegerList);
        System.out.println("Удален Integer[4]");
        customIntegerList.remove(4);
        System.out.println("Элементов: " + customIntegerList.getLength());
        System.out.println(customIntegerList);
        System.out.println("Integer[4]: " + customIntegerList.elementAt(4));
        System.out.println("Содержится ли элемент \"8\": " + customIntegerList.contains(8));
        System.out.println("Содержится ли элемент \"22\": " + customIntegerList.contains(22));
        System.out.println("Список очищен");
        customIntegerList.clear();
        System.out.println("Элементов: " + customIntegerList.getLength());
        System.out.println(customIntegerList);
        System.out.println();

        System.out.println("Создан список String[10]");
        MyArrayList<String> customStringList =new MyArrayList<String>(8);
        for (int i = 0; i < 10; i++) customStringList.add("Строка " + i);
        System.out.println("Элементов: " + customStringList.getLength());
        System.out.println(customStringList);
        System.out.println("Удален String[7]");
        customStringList.remove(7);
        System.out.println("Элементов: " + customStringList.getLength());
        System.out.println(customStringList);
        System.out.println("String[2]: " + customStringList.elementAt(2));
        System.out.println("Содержится ли элемент \"Строка 1\": " + customStringList.contains("Строка 1"));
        System.out.println("Содержится ли элемент \"Строка 11\": " + customStringList.contains("Строка 11"));
        System.out.println("Список очищен");
        customStringList.clear();
        System.out.println("Элементов: " + customStringList.getLength());
        System.out.println(customStringList);
        System.out.println();

        System.out.println("Создан список Student[15]");
        MyArrayList<Student> customStudentList =new MyArrayList<Student>();
        for (int i = 0; i < 15; i++) {
            customStudentList.add(new Student("Студент " + i, "GroupName"));
        }
        System.out.println("Элементов: " + customStudentList.getLength());
        System.out.println(customStudentList);
        System.out.println("Удален Student[5]");
        customStudentList.remove(5);
        System.out.println("Элементов: " + customStudentList.getLength());
        System.out.println(customStudentList);
        System.out.println("Student[9]: " + customStudentList.elementAt(9));
        System.out.println("Содержится ли элемент \"Студент 1\" из группы \"GroupName\": "
                + customStudentList.contains(new Student("Студент 1", "GroupName")));
        System.out.println("Содержится ли элемент \"Студент 1\" из группы \"GroupName1\": "
                + customStudentList.contains(new Student("Студент 1", "GroupName1")));
        System.out.println("Содержится ли элемент \"Студент 22\" из группы \"GroupName\": "
                + customStudentList.contains(new Student("Студент 22", "GroupName")));
        System.out.println("Список очищен");
        customStudentList.clear();
        System.out.println("Элементов: " + customStringList.getLength());
        System.out.println(customStringList);
    }
}
