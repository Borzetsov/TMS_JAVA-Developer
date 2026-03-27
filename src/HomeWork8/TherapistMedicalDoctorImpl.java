/**
 * Classname    Therapist
 * @version     0.02
 * @author      Aleksei Borzetsov
 * date         27.03.2026
 */
 
package HomeWork8;

public class TherapistMedicalDoctorImpl implements MedicalDoctor {

    public static int MD_CODE = 0;

    private String name;

    public TherapistMedicalDoctorImpl(String name) {
        this.name = name;
    }

    @Override
    public void cure(Patient patient) {
        System.out.println("Терапевт " + this.name + "ведет прием пациента " +
                           patient.getName() + ".");
        this.assignDoctor(patient);
        System.out.println("Прием окончен.");
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void assignDoctor(Patient patient) {
        int treatmentPlanOfThePatient = patient.getTreatmentPlan();
        if (treatmentPlanOfThePatient == SurgeonMedicalDoctorImpl.MD_CODE) {

        }
        else if (treatmentPlanOfThePatient == DentistMedicalDoctorImpl.MD_CODE) {

        }
        else {

        }
        System.out.println("Терапевт " + this.name + "назначает пациенту " +
                patient.getName() + " прием у врача ");
    }
}
