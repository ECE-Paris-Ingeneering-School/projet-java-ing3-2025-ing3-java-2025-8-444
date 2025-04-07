package dao;

import modele.*;
import java.sql.*;
import java.util.*;

public class PatientDAO {
    private Connection connection;

    public PatientDAO(Connection connection) {
        this.connection = connection;
    }

    public Patient findByEmailAndPassword(String email, String password) {
        Patient patient = null;
        String sql = "SELECT * FROM Patient WHERE email = ? AND mot_de_passe = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                patient = new Patient(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("mot_de_passe"),
                    rs.getString("type_patient")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patient";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                patients.add(new Patient(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("mot_de_passe"),
                    rs.getString("type_patient")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }
    public boolean save(Patient patient) {
    String sql = "INSERT INTO Patient (nom, prenom, email, mot_de_passe, type_patient) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, patient.getNom());
        stmt.setString(2, patient.getPrenom());
        stmt.setString(3, patient.getEmail());
        stmt.setString(4, patient.getMotDePasse());
        stmt.setString(5, patient.getTypePatient());
        int rows = stmt.executeUpdate();
        return rows > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}