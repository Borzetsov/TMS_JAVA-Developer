/**
 * Classname    HomeWork8
 * @version     0.03
 * @author      Aleksei Borzetsov
 * date         27.03.2026
 */
 
package HomeWork8;

public class HomeWork8 {

    public void run() {

        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №8 | Выдано 23.03.2026 ;");
        System.out.println();

        System.out.println("Задача 1");
        System.out.println();
        Clinic clinic = new Clinic(new TherapistMedicalDoctorImpl("Авиценна"),
                                   new SurgeonMedicalDoctorImpl("Преображенский"),
                                   new DentistMedicalDoctorImpl("Менгеле"));

        Patient patient1 = new Patient("John Weak", 0);
        clinic.admitPatient(patient1);
        System.out.println();

        System.out.println("Задача *");
        System.out.println();
        Apple apple = new Apple();
        apple.printColor();
        ApplePainter painter = new ApplePainter();
        painter.paintApple(apple);
        apple.printColor();
    }
}
