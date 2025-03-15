import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;
class Doctor {
    private String name;
    private String specialization;
    private int id;

    public Doctor(String name, String specialization, int id) {
        this.name = name;
        this.specialization = specialization;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialize() {
        return specialization;
    }

    public int getId() {
        return id;
    }
}
class Patient {
    private String name;
    private String caseOfInjury;
    private int id;
    private int age;

    public Patient(String name, String caseOfInjury, int id, int age) {
        this.name = name;
        this.caseOfInjury = caseOfInjury;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getCase() {
        return caseOfInjury;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }
}

class hospital_management {
    public void register_patient(Patient patient) {
        DatabaseHelper.registerPatient(patient);
    }

    public void register_doctor(Doctor doctor) {
        DatabaseHelper.registerDoctor(doctor);
    }

    public void assign_doctor_to_patient(int patient_id, int doctor_id) {
        DatabaseHelper.assignDoctorToPatient(patient_id, doctor_id);
    }

    public void addObservationsforpatient(int patient_id, String type, String val) {
        DatabaseHelper.addObservation(patient_id, type, val);
    }

    public void get_patient_info(int patient_id) {
        DatabaseHelper.getPatientInfo(patient_id);
    }
    public void deletePatient(int patientId) {
    DatabaseHelper.deletePatient(patientId);
}

public void updateDoctor(int doctorId, String name, String specialization) {
    DatabaseHelper.updateDoctor(doctorId, name, specialization);
}

public void updatePatient(int patientId, String name, int age, String caseOfInjury) {
    DatabaseHelper.updatePatient(patientId, name, age, caseOfInjury);
}
}

public class hospital_info_system {
    public static void main(String[] args) {
         // Initialize the database and create tables if needed
         DatabaseHelper.setupDatabase();
        Scanner scanner = new Scanner(System.in);
        hospital_management hop = new hospital_management();

        System.out.println("Enter the number of doctors:");
        int numDoctors = scanner.nextInt();
        for (int i = 0; i < numDoctors; i++) {
            System.out.println("Enter name, specialization, doctor ID:");
            scanner.nextLine(); // Consume leftover newline
            String name = scanner.nextLine();
            String specialization = scanner.next();
            int doctorId = scanner.nextInt();
            Doctor doc = new Doctor(name, specialization, doctorId);
            hop.register_doctor(doc);
        }

        System.out.println("Enter the number of patients:");
        int numPatients = scanner.nextInt();
        for (int i = 0; i < numPatients; i++) {
            System.out.println("Enter name, case of injury, patient ID, age:");
            scanner.nextLine(); // Consume leftover newline
            String name = scanner.nextLine();
            String caseOfInjury = scanner.next();
            int patientId = scanner.nextInt();
            int age = scanner.nextInt();
            Patient pat = new Patient(name, caseOfInjury, patientId, age);
            hop.register_patient(pat);
        }

 int choice;
    do {
        System.out.println("Enter 0 to exit, 1 to assign doctor and add observations, 2 to delete a patient, 3 to update doctor details, 4 to update patient details, 5 to display patient's info:");
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter patient ID:");
                int patientId = scanner.nextInt();
                System.out.println("Enter doctor ID to assign:");
                int doctorId = scanner.nextInt();
                scanner.nextLine();
                hop.assign_doctor_to_patient(patientId, doctorId);

                System.out.println("Enter number of observations:");
                int numObservations = scanner.nextInt();
                scanner.nextLine();
                for (int i = 0; i < numObservations; i++) {
                    System.out.println("Enter type of observation:");
                    String type = scanner.nextLine();
                    System.out.println("Enter value of observation:");
                    String value = scanner.nextLine();
                    hop.addObservationsforpatient(patientId, type, value);
                }
                System.out.println("Patient's info:");
                hop.get_patient_info(patientId);
                break;

            case 2:
                System.out.println("Enter patient ID to delete:");
                int deletePatientId = scanner.nextInt();
                hop.deletePatient(deletePatientId);
                break;

            case 3:
                System.out.println("Enter doctor ID to update:");
                int updateDoctorId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter new name:");
                String newDoctorName = scanner.nextLine();
                System.out.println("Enter new specialization:");
                String newSpecialization = scanner.nextLine();
                hop.updateDoctor(updateDoctorId, newDoctorName, newSpecialization);
                break;

            case 4:
                System.out.println("Enter patient ID to update:");
                int updatePatientId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter new name:");
                String newPatientName = scanner.nextLine();
                System.out.println("Enter new age:");
                int newAge = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter new case of injury:");
                String newCase = scanner.nextLine();
                hop.updatePatient(updatePatientId, newPatientName, newAge, newCase);
                break;
                
            case 5:
                 System.out.println("Enter patient ID you want to view:");
                  patientId = scanner.nextInt();
                  System.out.println("Patient's info:");
                  hop.get_patient_info(patientId);
                  break;
        }
    } while (choice != 0);

    scanner.close();


       
    }
}
