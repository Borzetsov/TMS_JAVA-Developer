/**
 * Classname    Student
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         17.04.2026
 */
 
package HomeWork15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    public static final double MINIMAL_AVERAGE_SCORE = 3.0;

    private String name;
    private String groupName;
    private int year;
    private Map<String, ArrayList<Integer>> marks = new HashMap<>();

    public Student(String name, String groupName) {
        this.name = name;
        this.groupName = groupName;
        this.year = 1;
        /*Назначить список предметов*/
        for (int i = 0; i < Courses.values().length; i++) {
            marks.put(Courses.values()[i].toString(), new ArrayList<>());
        }
    }

    public void setMark(Courses course, int mark) {
        if (this.marks.containsKey(course.name())) {
            this.marks.get(course.name()).add(Math.min(mark, 5));
        }
    }

    /**
     * Перевести на следующий курс
     */
    public void transferStudent() {
        this.year++;
    }

    public String getName() {
        return this.name;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public int getYear() {
        return this.year;
    }

    private double getAverageScore() {
        double totalScore = 0;
        int marksAmount = 0;
        for (int i = 0; i < Courses.values().length; i++) {
            if (this.marks.containsKey(Courses.values()[i].toString())) {
                for (int currentMark : this.marks.get(Courses.values()[i].toString())) {
                    totalScore += currentMark;
                }
                marksAmount += this.marks.get(Courses.values()[i].toString()).size();
            }
        }
        return totalScore / marksAmount;
    }

    public static void session(List<Student> students) {
        System.out.println("Наступила сессия");
        /*Отчислить студентов, чей средний балл ниже проходного*/
        students.removeIf(student -> student.getAverageScore() < MINIMAL_AVERAGE_SCORE);
        /**/
        for (Student currentStudent : students) currentStudent.transferStudent();
    }

    public static void printStudents(List<Student> students, int year) {
        System.out.println("Список студентов " + year + " курса:");
        int i = 1;
        for (Student currentStudent : students) {
            if (currentStudent.getYear() == year) {
                System.out.println(i + ".\t" + currentStudent + ".");
                i++;
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return this.getName() + ".\tГруппа:\t" + this.getGroupName();
    }
}
