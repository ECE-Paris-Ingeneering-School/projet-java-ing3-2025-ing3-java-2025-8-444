package dao;

import exceptions.DaoOperationException;
import model.Admin;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.exceptionsConstantes.ERREUR_DAO_ADMIN;

/**
 * DAO (Data Access Object) pour la gestion des administrateurs dans la base de données.
 * Implémente les opérations CRUD pour les objets {@link Admin}.
 */
public class AdminDAO implements DAO<Admin> {

    /**
     * Récupère un administrateur par son identifiant.
     *
     * @param id L'identifiant de l'administrateur.
     * @return L'objet {@link Admin} correspondant, ou {@code null} s'il n'existe pas.
     * @throws DaoOperationException En cas d'erreur lors de l'accès à la base de données.
     */
    @Override
    public Admin get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.* FROM utilisateur u JOIN admin a ON u.id = a.id WHERE u.id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Admin(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_ADMIN, e);
        }
    }

    /**
     * Récupère tous les administrateurs de la base de données.
     *
     * @return Une liste de tous les objets {@link Admin}.
     * @throws DaoOperationException En cas d'erreur lors de l'accès à la base de données.
     */
    @Override
    public List<Admin> getAll() {
        List<Admin> admins = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.* FROM utilisateur u JOIN admin a ON u.id = a.id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                admins.add(new Admin(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe")
                ));
            }
            return admins;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_ADMIN, e);
        }
    }

    /**
     * Enregistre un nouvel administrateur dans la base de données.
     *
     * @param admin L'administrateur à enregistrer.
     * @return {@code true} si l'enregistrement a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur lors de l'accès à la base de données.
     */
    @Override
    public boolean save(Admin admin) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            String sqlUser = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, type) VALUES (?, ?, ?, ?, 'admin')";
            PreparedStatement stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            stmtUser.setString(1, admin.getNom());
            stmtUser.setString(2, admin.getPrenom());
            stmtUser.setString(3, admin.getEmail());
            stmtUser.setString(4, admin.getMotDePasse());
            stmtUser.executeUpdate();

            ResultSet generatedKeys = stmtUser.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                admin.setId(userId);

                String sqlAdmin = "INSERT INTO admin (id) VALUES (?)";
                PreparedStatement stmtAdmin = conn.prepareStatement(sqlAdmin);
                stmtAdmin.setInt(1, userId);
                stmtAdmin.executeUpdate();

                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_ADMIN, e);
        }
    }

    /**
     * Met à jour les informations d'un administrateur existant.
     *
     * @param admin L'administrateur avec les nouvelles informations.
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur lors de l'accès à la base de données.
     */
    @Override
    public boolean update(Admin admin) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE utilisateur SET nom=?, prenom=?, email=?, mot_de_passe=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getNom());
            stmt.setString(2, admin.getPrenom());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getMotDePasse());
            stmt.setInt(5, admin.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_ADMIN, e);
        }
    }

    /**
     * Supprime un administrateur de la base de données.
     *
     * @param admin L'administrateur à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur lors de l'accès à la base de données.
     */
    @Override
    public boolean delete(Admin admin) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM utilisateur WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, admin.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_ADMIN, e);
        }
    }
}
