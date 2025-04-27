package dao;

import exceptions.DaoOperationException;
import model.Patient;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.exceptionsConstantes.ERREUR_DAO_PATIENT;

public class PatientDAO implements DAO<Patient> {

    @Override
    public Patient get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.* FROM utilisateur u JOIN patient p ON u.id = p.id WHERE u.id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Patient(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe")
                );
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_PATIENT, e);
        }
        return null;
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patients = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.* FROM utilisateur u JOIN patient p ON u.id = p.id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe")
                ));
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_PATIENT, e);
        }
        return patients;
    }

    @Override
    public boolean save(Patient patient) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            String sqlUser = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, type) VALUES (?, ?, ?, ?, 'patient')";
            PreparedStatement stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            stmtUser.setString(1, patient.getNom());
            stmtUser.setString(2, patient.getPrenom());
            stmtUser.setString(3, patient.getEmail());
            stmtUser.setString(4, patient.getMotDePasse());
            stmtUser.executeUpdate();

            ResultSet generatedKeys = stmtUser.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                patient.setId(userId);

                String sqlPatient = "INSERT INTO patient (id) VALUES (?)";
                PreparedStatement stmtPatient = conn.prepareStatement(sqlPatient);
                stmtPatient.setInt(1, userId);
                stmtPatient.executeUpdate();

                conn.commit();
                return true;
            }

            conn.rollback();
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_PATIENT, e);
        }
        return false;
    }

    @Override
    public boolean update(Patient patient) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE utilisateur SET nom=?, prenom=?, email=?, mot_de_passe=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setString(3, patient.getEmail());
            stmt.setString(4, patient.getMotDePasse());
            stmt.setInt(5, patient.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_PATIENT, e);
        }
    }

    @Override
    public boolean delete(Patient patient) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM utilisateur WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patient.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_PATIENT, e);
        }
    }
}
