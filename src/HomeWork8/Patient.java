/**
 * Classname    Patient
 * @version     0.02
 * @author      Aleksei Borzetsov
 * date         27.03.2026
 */
 
package HomeWork8;

public class Patient {

    private int treatmentPlan = 0;
    private MedicalDoctor doctor;
    private String name = "";

    public Patient(String name, int treatmentPlan) {
        this.name = name;
        this.treatmentPlan = treatmentPlan;
    }

    public void setDoctor(MedicalDoctor doctor) {
        this.doctor = doctor;
    }

    public int getTreatmentPlan() {
        return this.treatmentPlan;
    }

    public String getName() {
        return this.name;
    }
}
