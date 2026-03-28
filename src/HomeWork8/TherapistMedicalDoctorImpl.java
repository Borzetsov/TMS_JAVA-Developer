/**
 * Classname    Therapist
 * @version     0.03
 * @author      Aleksei Borzetsov
 * date         27.03.2026
 */
 
package HomeWork8;

public class TherapistMedicalDoctorImpl implements MedicalDoctor {

    private String name;

    public TherapistMedicalDoctorImpl(String name) {
        this.name = name;
    }

    @Override
    public void cure(Patient patient) {
        System.out.println("Терапевт " + this.name + " ведет прием пациента " +
                           patient.getName() + ".");
        System.out.println("Прием окончен.");
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void assignDoctor(Clinic clinic, Patient patient) {
        int treatmentPlanOfThePatient = patient.getTreatmentPlan();
        if (treatmentPlanOfThePatient == Clinic.SURGEON_CODE) {
            patient.setDoctor(clinic.getSurgeon());
        }
        else if (treatmentPlanOfThePatient == Clinic.DENTIST_CODE) {
            patient.setDoctor(clinic.getDentist());
        }
        else {
            patient.setDoctor(this);
        }
    }
}
