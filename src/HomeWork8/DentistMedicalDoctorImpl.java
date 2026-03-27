/**
 * Classname    Dentist
 * @version     0.02
 * @author      Aleksei Borzetsov
 * date         27.03.2026
 */
 
package HomeWork8;

public class DentistMedicalDoctorImpl implements MedicalDoctor {

    public static int MD_CODE = 2;
    private String name;

    public DentistMedicalDoctorImpl(String name) {
        this.name = name;
    }

    @Override
    public void cure(Patient patient) {
        System.out.println("Стоматолог " + this.name + "лечит зубы пациента " +
                           patient.getName() + ".");
        System.out.println("Прием окончен.");
    }

    @Override
    public String getName() {
        return this.name;
    }
}
