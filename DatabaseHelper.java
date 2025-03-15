import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;

public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root"; // Change to your MySQL username
    private static final String PASSWORD = "password"; // Change to your MySQL password

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
     public static void setupDatabase() {
    try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
         stmt.executeUpdate("DROP TABLE IF EXISTS Observations");
        stmt.executeUpdate("DROP TABLE IF EXISTS Patients");
        stmt.executeUpdate("DROP TABLE IF EXISTS Doctors");
        
         // Create Doctors table
        String createDoctors = 
            "CREATE TABLE IF NOT EXISTS Doctors (" +
            "id INT PRIMARY KEY, " +
            "name VARCHAR(255) NOT NULL, " +
            "specialization VARCHAR(255) NOT NULL" +
            ")";
        stmt.executeUpdate(createDoctors);

        // Create Patients table
        String createPatients = 
            "CREATE TABLE IF NOT EXISTS Patients (" +
            "id INT PRIMARY KEY, " +
            "name VARCHAR(255) NOT NULL, " +
            "age INT NOT NULL, " +
            "case_of_injury TEXT NOT NULL, " +
            "doctor_assigned_id INT, " +
            "FOREIGN KEY (doctor_assigned_id) REFERENCES Doctors(id) ON DELETE SET NULL" +
            ")";
        stmt.executeUpdate(createPatients);

        // Create Observations table
        String createObservations = 
            "CREATE TABLE IF NOT EXISTS Observations (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "patient_id INT NOT NULL, " +
            "type VARCHAR(255) NOT NULL, " +
            "value TEXT NOT NULL, " +
            "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "FOREIGN KEY (patient_id) REFERENCES Patients(id) ON DELETE CASCADE" +
            ")";
        stmt.executeUpdate(createObservations);
      System.out.println("Database setup completed. Old data cleared, and tables are ready.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public static void registerPatient(Patient patient) {
        String query = "INSERT INTO Patients (id, name, age, case_of_injury, doctor_assigned_id) VALUES (?, ?, ?, ?, NULL)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, patient.getId());
            stmt.setString(2, patient.getName());
            stmt.setInt(3, patient.getAge());
            stmt.setString(4, patient.getCase());
            stmt.executeUpdate();
            System.out.println("Patient registered in the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void registerDoctor(Doctor doctor) {
        String query = "INSERT INTO Doctors (id, name, specialization) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, doctor.getId());
            stmt.setString(2, doctor.getName());
            stmt.setString(3, doctor.getSpecialize());
            stmt.executeUpdate();
            System.out.println("Doctor registered in the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void assignDoctorToPatient(int patientId, int doctorId) {
        String query = "UPDATE Patients SET doctor_assigned_id = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, doctorId);
            stmt.setInt(2, patientId);
            stmt.executeUpdate();
            System.out.println("Doctor assigned to patient in the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addObservation(int patientId, String type, String value) {
        String query = "INSERT INTO Observations (patient_id, type, value) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            stmt.setString(2, type);
            stmt.setString(3, value);
            stmt.executeUpdate();
            System.out.println("Observation added to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getPatientInfo(int patientId) {
        String query = "SELECT * FROM Patients WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Patient ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Case of Injury: " + rs.getString("case_of_injury"));
            } else {
                System.out.println("Patient not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deletePatient(int patientId) {
    String query = "DELETE FROM Patients WHERE id = ?";
    try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, patientId);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Patient record deleted successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public static void updateDoctor(int doctorId, String name, String specialization) {
    String query = "UPDATE Doctors SET name = ?, specialization = ? WHERE id = ?";
    try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, name);
        stmt.setString(2, specialization);
        stmt.setInt(3, doctorId);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Doctor record updated successfully.");
        } else {
            System.out.println("Doctor not found.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public static void updatePatient(int patientId, String name, int age, String caseOfInjury) {
    String query = "UPDATE Patients SET name = ?, age = ?, case_of_injury = ? WHERE id = ?";
    try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.setString(3, caseOfInjury);
        stmt.setInt(4, patientId);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Patient record updated successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
