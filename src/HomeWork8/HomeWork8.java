/**
 * Classname    HomeWork8
 * @version     0.02
 * @author      Aleksei Borzetsov
 * date         27.03.2026
 */
 
package HomeWork8;

public class HomeWork8 {

    public void run() {

        /*Шапка отчета*/
        System.out.println("; Алексей Борзецов | Домашняя работа №8 | Выдано 23.03.2026 ;");
        System.out.println();

        Clinic clinic = new Clinic(/*new TherapistMedicalDoctorImpl("Авиценна"),*/
                                   new SurgeonMedicalDoctorImpl("Преображенский"),
                                   new DentistMedicalDoctorImpl("Менгеле"));

        Patient patient1 = new Patient("John Weak", 0);
        clinic.admitPatient(patient1);
    }
}
