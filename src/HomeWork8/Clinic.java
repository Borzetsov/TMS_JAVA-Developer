/**
 * Classname    Clinic
 * @version     0.01
 * @author      Aleksei Borzetsov
 * date         27.03.2026
 */
 
package HomeWork8;

public class Clinic {

    private MedicalDoctor[] doctors;

    public Clinic (MedicalDoctor... doctors) {
        this.doctors = doctors;
    }

    public void admitPatient(Patient patient) {
        TherapistMedicalDoctorImpl availableTherapist = this.getTherapist();
        if (availableTherapist == null) {
            System.out.println("Клиника не может принять пациента " + patient.getName() + ".");
            return;
        }

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
