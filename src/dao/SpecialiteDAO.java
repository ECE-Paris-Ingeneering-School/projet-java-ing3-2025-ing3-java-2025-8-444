package dao;

import exceptions.DaoOperationException;
import model.Specialite;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.exceptionsConstantes.ERREUR_DAO_SPECIALITE;

/**
 * DAO (Data Access Object) pour la gestion des spécialités dans la base de données.
 * Implémente les opérations CRUD pour les objets {@link Specialite}.
 */
public class SpecialiteDAO implements DAO<Specialite> {

    /**
     * Cette méthode n'est pas supportée pour {@link Specialite}.
     * Utiliser {@link #getByName(String)} à la place.
     *
     * @param id L'identifiant de l'entité (non utilisé pour Specialite).
     * @return Ne retourne jamais car l'opération n'est pas supportée.
     * @throws UnsupportedOperationException Toujours levée pour cette méthode.
     */
    @Override
    public Specialite get(int id) {
        throw new UnsupportedOperationException("Utiliser getByName(String nom) à la place.");
    }

    /**
     * Récupère une spécialité par son nom.
     *
     * @param nom Le nom de la spécialité.
     * @return L'objet {@link Specialite} correspondant ou {@code null} si non trouvé.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    public Specialite getByName(String nom) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM specialite WHERE nom = ?");
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Specialite(rs.getString("nom"), rs.getString("description"));
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALITE, e);
        }
        return null;
    }

    /**
     * Récupère toutes les spécialités de la base de données.
     *
     * @return Une liste de toutes les spécialités.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public List<Specialite> getAll() {
        List<Specialite> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM specialite");
            while (rs.next()) {
                list.add(new Specialite(rs.getString("nom"), rs.getString("description")));
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALITE, e);
        }
        return list;
    }

    /**
     * Sauvegarde une nouvelle spécialité dans la base de données.
     *
     * @param s La spécialité à sauvegarder.
     * @return {@code true} si l'insertion a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public boolean save(Specialite s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO specialite (nom, description) VALUES (?, ?)");
            stmt.setString(1, s.getNom());
            stmt.setString(2, s.getDescription());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALITE, e);
        }
    }

    /**
     * Met à jour la description d'une spécialité existante.
     *
     * @param s La spécialité avec les nouvelles données.
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public boolean update(Specialite s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE specialite SET description=? WHERE nom=?");
            stmt.setString(1, s.getDescription());
            stmt.setString(2, s.getNom());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALITE, e);
        }
    }

    /**
     * Supprime une spécialité de la base de données.
     *
     * @param s La spécialité à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public boolean delete(Specialite s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM specialite WHERE nom=?");
            stmt.setString(1, s.getNom());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALITE, e);
        }
    }
}
