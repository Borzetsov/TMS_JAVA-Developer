/**
 * Classname    Clinic
 * @version     0.02
 * @author      Aleksei Borzetsov
 * date         27.03.2026
 */
 
package HomeWork8;

public class Clinic {

    public static final int THERAPIST_CODE = 0;
    public static final int SURGEON_CODE = 1;
    public static final int DENTIST_CODE = 2;

    private MedicalDoctor[] doctors;

    public Clinic (MedicalDoctor... doctors) {
        this.doctors = doctors;
    }

    public void admitPatient(Patient patient) {
        System.out.println("Пациент " + patient.getName() + " обратился в клинику.");
        //Отправить к терапевту.
        //Найти свободного терапевта.
        TherapistMedicalDoctorImpl availableTherapist = this.getTherapist();
        if (availableTherapist == null) {
            System.out.println("Клиника не может принять пациента " + patient.getName() + ".");
            return;
        }
        //Терапевт назначает врача
        availableTherapist.assignDoctor(this, patient);
        //Назначенный врач лечит пациента
        MedicalDoctor doctor = patient.getDoctor();
        if (doctor == null) {
            System.out.println("Клиника не может принять пациента " + patient.getName() +
                               ", нет соответствующего специалиста.");
            return;
        }
        System.out.println("Терапевт " + availableTherapist.getName() + " назначает пациенту " +
                           patient.getName() + " прием у врача " + doctor.getName() + ".");
        doctor.cure(patient);
        System.out.println("Пациент " + patient.getName() + " завершил визит в клинику.");
    }

    public TherapistMedicalDoctorImpl getTherapist() {
        for (MedicalDoctor doctor : this.doctors) {
            if (doctor.getClass().equals(TherapistMedicalDoctorImpl.class)) {
                return (TherapistMedicalDoctorImpl) doctor;
            }
        }
        //Если такого специалиста нет в штате клиники
        return null;
    }

    public SurgeonMedicalDoctorImpl getSurgeon() {
        for (MedicalDoctor doctor : this.doctors) {
            if (doctor.getClass().equals(SurgeonMedicalDoctorImpl.class)) {
                return (SurgeonMedicalDoctorImpl)doctor;
            }
        }
        //Если такого специалиста нет в штате клиники
        return null;
    }

    public DentistMedicalDoctorImpl getDentist() {
        for (MedicalDoctor doctor : this.doctors) {
            if (doctor.getClass().equals(DentistMedicalDoctorImpl.class)) {
                return (DentistMedicalDoctorImpl) doctor;
            }
        }
        //Если такого специалиста нет в штате клиники
        return null;
    }
}
