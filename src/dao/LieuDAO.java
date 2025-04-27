package dao;

/* Sources :
https://drive.google.com/file/d/1mvTcyiJjID0ZQtiLQuOi2GhnBmaQY-aW/view
https://drive.google.com/file/d/1jYcbKG3FMyEa1OpaPpYy5Om3dF3RCXky/view
https://openclassrooms.com/fr/courses/2434016-developpez-des-sites-web-avec-java-ee/2438696-utiliser-le-modele-dao
*/

import exceptions.DaoOperationException;
import model.Lieu;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.exceptionsConstantes.ERREUR_DAO_LIEU;

/**
 * DAO (Data Access Object) pour la gestion des lieux dans la base de données.
 * Implémente les opérations CRUD pour les objets {@link Lieu}.
 */
public class LieuDAO implements DAO<Lieu> {

    /**
     * Constructeur par défaut pour l'initialisation de l'objet LieuDAO.
     * Il initialise l'objet sans paramètres spécifiques.
     */
    public LieuDAO() {
        // Pas de code nécessaire si le constructeur est vide
    }

    /**
     * Récupère un lieu par son identifiant.
     *
     * @param id L'identifiant du lieu.
     * @return L'objet {@link Lieu} correspondant ou {@code null} s'il n'existe pas.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public Lieu get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM lieu WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Lieu(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("ville"),
                        rs.getString("code_postal")
                );
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_LIEU, e);
        }
        return null;
    }

    /**
     * Récupère tous les lieux de la base de données.
     *
     * @return Une liste de tous les objets {@link Lieu}.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public List<Lieu> getAll() {
        List<Lieu> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM lieu");
            while (rs.next()) {
                list.add(new Lieu(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("ville"),
                        rs.getString("code_postal")
                ));
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_LIEU, e);
        }
        return list;
    }

    /**
     * Sauvegarde un nouveau lieu dans la base de données.
     *
     * @param l Le lieu à sauvegarder.
     * @return {@code true} si l'insertion a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public boolean save(Lieu l) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO lieu (nom, adresse, ville, code_postal) VALUES (?, ?, ?, ?)");
            stmt.setString(1, l.getNom());
            stmt.setString(2, l.getAdresse());
            stmt.setString(3, l.getVille());
            stmt.setString(4, l.getCodePostal());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_LIEU, e);
        }
    }

    /**
     * Met à jour un lieu existant dans la base de données.
     *
     * @param l Le lieu avec les nouvelles informations.
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public boolean update(Lieu l) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE lieu SET nom=?, adresse=?, ville=?, code_postal=? WHERE id=?");
            stmt.setString(1, l.getNom());
            stmt.setString(2, l.getAdresse());
            stmt.setString(3, l.getVille());
            stmt.setString(4, l.getCodePostal());
            stmt.setInt(5, l.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_LIEU, e);
        }
    }

    /**
     * Supprime un lieu existant de la base de données.
     *
     * @param l Le lieu à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public boolean delete(Lieu l) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM lieu WHERE id=?");
            stmt.setInt(1, l.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_LIEU, e);
        }
    }
}
