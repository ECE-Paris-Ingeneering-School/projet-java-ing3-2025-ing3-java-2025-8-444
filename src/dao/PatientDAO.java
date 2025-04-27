package dao;

/* Sources :
https://drive.google.com/file/d/1mvTcyiJjID0ZQtiLQuOi2GhnBmaQY-aW/view
https://drive.google.com/file/d/1jYcbKG3FMyEa1OpaPpYy5Om3dF3RCXky/view
https://openclassrooms.com/fr/courses/2434016-developpez-des-sites-web-avec-java-ee/2438696-utiliser-le-modele-dao
*/

import exceptions.DaoOperationException;
import model.Patient;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.exceptionsConstantes.ERREUR_DAO_PATIENT;

/**
 * DAO (Data Access Object) pour la gestion des patients dans la base de données.
 * Implémente les opérations CRUD pour les objets {@link Patient}.
 */
public class PatientDAO implements DAO<Patient> {

    /**
     * Constructeur par défaut.
     * Nécessaire pour créer une instance de PatientDAO.
     */
    public PatientDAO() {
        // Aucune initialisation spécifique nécessaire
    }

    /**
     * Récupère un patient par son identifiant.
     *
     * @param id L'identifiant du patient.
     * @return L'objet {@link Patient} correspondant ou {@code null} s'il n'existe pas.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
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

    /**
     * Récupère tous les patients de la base de données.
     *
     * @return Une liste de tous les objets {@link Patient}.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
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

    /**
     * Sauvegarde un nouveau patient dans la base de données.
     *
     * @param patient Le patient à sauvegarder.
     * @return {@code true} si l'enregistrement a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
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

    /**
     * Met à jour un patient existant dans la base de données.
     *
     * @param patient Le patient avec les informations mises à jour.
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
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

    /**
     * Supprime un patient existant de la base de données.
     *
     * @param patient Le patient à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
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
