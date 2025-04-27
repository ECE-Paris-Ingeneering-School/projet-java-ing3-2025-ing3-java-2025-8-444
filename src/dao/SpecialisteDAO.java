package dao;

/* Sources :
https://drive.google.com/file/d/1mvTcyiJjID0ZQtiLQuOi2GhnBmaQY-aW/view
https://drive.google.com/file/d/1jYcbKG3FMyEa1OpaPpYy5Om3dF3RCXky/view
https://openclassrooms.com/fr/courses/2434016-developpez-des-sites-web-avec-java-ee/2438696-utiliser-le-modele-dao
*/

import exceptions.DaoOperationException;
import model.Specialiste;
import model.Specialite;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.exceptionsConstantes.ERREUR_DAO_SPECIALISTE;

/**
 * DAO (Data Access Object) pour la gestion des spécialistes dans la base de données.
 * Implémente les opérations CRUD pour les objets {@link Specialiste}.
 */
public class SpecialisteDAO implements DAO<Specialiste> {

    /**
     * Récupère un spécialiste par son identifiant.
     *
     * @param id L'identifiant du spécialiste.
     * @return L'objet {@link Specialiste} correspondant ou {@code null} s'il n'existe pas.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public Specialiste get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.nom, u.prenom, u.email, u.mot_de_passe, s.qualification, sp.nom AS spec_nom, sp.description " +
                    "FROM specialiste s " +
                    "JOIN utilisateur u ON s.id = u.id " +
                    "JOIN specialite sp ON s.specialite_nom = sp.nom " +
                    "WHERE s.id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Specialite specialite = new Specialite(rs.getString("spec_nom"), rs.getString("description"));
                return new Specialiste(
                        id,
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("qualification"),
                        specialite
                );
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALISTE, e);
        }
        return null;
    }

    /**
     * Récupère tous les spécialistes de la base de données.
     *
     * @return Une liste de tous les spécialistes.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public List<Specialiste> getAll() {
        List<Specialiste> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.id, u.nom, u.prenom, u.email, u.mot_de_passe, s.qualification, sp.nom AS spec_nom, sp.description " +
                    "FROM specialiste s " +
                    "JOIN utilisateur u ON s.id = u.id " +
                    "JOIN specialite sp ON s.specialite_nom = sp.nom";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Specialite specialite = new Specialite(rs.getString("spec_nom"), rs.getString("description"));
                list.add(new Specialiste(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("qualification"),
                        specialite
                ));
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALISTE, e);
        }
        return list;
    }

    /**
     * Sauvegarde un nouveau spécialiste dans la base de données.
     *
     * @param s Le spécialiste à sauvegarder.
     * @return {@code true} si l'enregistrement a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public boolean save(Specialiste s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            String sqlUser = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, type) VALUES (?, ?, ?, ?, 'specialiste')";
            PreparedStatement stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            stmtUser.setString(1, s.getNom());
            stmtUser.setString(2, s.getPrenom());
            stmtUser.setString(3, s.getEmail());
            stmtUser.setString(4, s.getMotDePasse());
            stmtUser.executeUpdate();

            ResultSet generatedKeys = stmtUser.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                s.setId(userId);

                String sqlSpecialiste = "INSERT INTO specialiste (id, qualification, specialite_nom) VALUES (?, ?, ?)";
                PreparedStatement stmtSpec = conn.prepareStatement(sqlSpecialiste);
                stmtSpec.setInt(1, userId);
                stmtSpec.setString(2, s.getQualification());
                stmtSpec.setString(3, s.getSpecialite().getNom());
                stmtSpec.executeUpdate();

                conn.commit();
                return true;
            }
            conn.rollback();
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALISTE, e);
        }
        return false;
    }

    /**
     * Met à jour les informations d'un spécialiste existant.
     *
     * @param s Le spécialiste avec les nouvelles informations.
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public boolean update(Specialiste s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE utilisateur u JOIN specialiste s ON u.id = s.id " +
                    "SET u.nom=?, u.prenom=?, u.email=?, u.mot_de_passe=?, s.qualification=?, s.specialite_nom=? WHERE u.id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, s.getNom());
            stmt.setString(2, s.getPrenom());
            stmt.setString(3, s.getEmail());
            stmt.setString(4, s.getMotDePasse());
            stmt.setString(5, s.getQualification());
            stmt.setString(6, s.getSpecialite().getNom());
            stmt.setInt(7, s.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALISTE, e);
        }
    }

    /**
     * Supprime un spécialiste de la base de données.
     *
     * @param s Le spécialiste à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public boolean delete(Specialiste s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM utilisateur WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, s.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALISTE, e);
        }
    }
    public int countSpecialistes() {
        int count = 0;


        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM specialiste";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * Récupère le nombre de spécialistes pour chaque spécialité.
     *
     * @return Une map associant le nom de chaque spécialité au nombre de spécialistes correspondants.
     * @throws DaoOperationException en cas d'erreur lors de l'accès à la base de données.
     */
    public Map<String, Integer> countSpecialistesBySpecialite() {
        Map<String, Integer> result = new HashMap<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT specialite_nom, COUNT(*) as nb_specialistes FROM specialiste GROUP BY specialite_nom";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String specialiteNom = rs.getString("specialite_nom");
                int count = rs.getInt("nb_specialistes");
                result.put(specialiteNom, count);
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALISTE, e);
        }

        return result;
    }

}
