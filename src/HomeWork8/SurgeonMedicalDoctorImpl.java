/**
 * Classname    Surgeon
 * @version     0.03
 * @author      Aleksei Borzetsov
 * date         27.03.2026
 */
 
package HomeWork8;

public class SurgeonMedicalDoctorImpl implements MedicalDoctor {

    private String name;

    public SurgeonMedicalDoctorImpl(String name) {
        this.name = name;
    }

    @Override
    public void cure(Patient patient) {
        System.out.println("Хирург " + this.name + " оперирует пациента " +
                           patient.getName() + ".");
        System.out.println("Прием окончен.");
    }

    @Override
    public String getName() {
        return this.name;
    }
}
