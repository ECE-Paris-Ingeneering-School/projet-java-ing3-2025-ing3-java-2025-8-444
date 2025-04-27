package dao;

import exceptions.DaoOperationException;
import model.Disponibilite;
import model.Lieu;
import model.Specialiste;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.exceptionsConstantes.ERREUR_DAO_DISPONIBILITE;

/**
 * DAO (Data Access Object) pour la gestion des disponibilités dans la base de données.
 * Implémente les opérations CRUD pour les objets {@link Disponibilite}.
 */
public class DisponibiliteDAO implements DAO<Disponibilite> {
    private final LieuDAO lieuDAO = new LieuDAO();
    private final SpecialisteDAO specialisteDAO = new SpecialisteDAO();

    /**
     * Constructeur par défaut.
     * Initialise le DAO sans configuration particulière.
     */
    public DisponibiliteDAO() {
        // Rien de spécifique à initialiser
    }

    /**
     * Récupère une disponibilité par son identifiant.
     *
     * @param id L'identifiant de la disponibilité.
     * @return L'objet {@link Disponibilite} correspondant ou {@code null} s'il n'existe pas.
     * @throws DaoOperationException En cas d'erreur lors de l'accès à la base de données.
     */
    @Override
    public Disponibilite get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM disponibilite WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Lieu lieu = lieuDAO.get(rs.getInt("lieu_id"));
                Specialiste specialiste = specialisteDAO.get(rs.getInt("specialiste_id"));
                return new Disponibilite(
                        rs.getInt("id"),
                        specialiste,
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("heure_debut").toLocalTime(),
                        rs.getTime("heure_fin").toLocalTime(),
                        lieu,
                        rs.getBoolean("est_disponible")
                );
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_DISPONIBILITE, e);
        }
        return null;
    }

    /**
     * Récupère toutes les disponibilités.
     *
     * @return Une liste de toutes les disponibilités.
     * @throws DaoOperationException En cas d'erreur lors de l'accès à la base de données.
     */
    @Override
    public List<Disponibilite> getAll() {
        List<Disponibilite> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM disponibilite");

            while (rs.next()) {
                Lieu lieu = lieuDAO.get(rs.getInt("lieu_id"));
                Specialiste specialiste = specialisteDAO.get(rs.getInt("specialiste_id"));
                list.add(new Disponibilite(
                        rs.getInt("id"),
                        specialiste,
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("heure_debut").toLocalTime(),
                        rs.getTime("heure_fin").toLocalTime(),
                        lieu,
                        rs.getBoolean("est_disponible")
                ));
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_DISPONIBILITE, e);
        }
        return list;
    }

    /**
     * Récupère toutes les disponibilités pour un spécialiste donné.
     *
     * @param specialisteId L'identifiant du spécialiste.
     * @return Une liste des disponibilités du spécialiste.
     * @throws DaoOperationException En cas d'erreur lors de l'accès à la base de données.
     */
    public List<Disponibilite> getAllForSpecialiste(int specialisteId) {
        List<Disponibilite> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM disponibilite WHERE specialiste_id = ?");
            stmt.setInt(1, specialisteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Lieu lieu = lieuDAO.get(rs.getInt("lieu_id"));
                Specialiste specialiste = specialisteDAO.get(rs.getInt("specialiste_id"));

                list.add(new Disponibilite(
                        rs.getInt("id"),
                        specialiste,
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("heure_debut").toLocalTime(),
                        rs.getTime("heure_fin").toLocalTime(),
                        lieu,
                        rs.getBoolean("est_disponible")
                ));
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_DISPONIBILITE, e);
        }
        return list;
    }

    /**
     * Sauvegarde une nouvelle disponibilité dans la base de données.
     *
     * @param d La disponibilité à sauvegarder.
     * @return {@code true} si l'opération a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur lors de l'accès à la base de données.
     */
    @Override
    public boolean save(Disponibilite d) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO disponibilite (specialiste_id, date, heure_debut, heure_fin, lieu_id, est_disponible) VALUES (?, ?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, d.getSpecialiste().getId());
            stmt.setDate(2, java.sql.Date.valueOf(d.getDate()));
            stmt.setTime(3, Time.valueOf(d.getHeureDebut()));
            stmt.setTime(4, Time.valueOf(d.getHeureFin()));
            stmt.setInt(5, d.getLieu().getId());
            stmt.setBoolean(6, d.isEstDisponible());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_DISPONIBILITE, e);
        }
    }

    /**
     * Met à jour une disponibilité existante.
     *
     * @param d La disponibilité avec les nouvelles informations.
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur lors de l'accès à la base de données.
     */
    @Override
    public boolean update(Disponibilite d) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE disponibilite SET specialiste_id=?, date=?, heure_debut=?, heure_fin=?, lieu_id=?, est_disponible=? WHERE id=?"
            );
            stmt.setInt(1, d.getSpecialiste().getId());
            stmt.setDate(2, java.sql.Date.valueOf(d.getDate()));
            stmt.setTime(3, Time.valueOf(d.getHeureDebut()));
            stmt.setTime(4, Time.valueOf(d.getHeureFin()));
            stmt.setInt(5, d.getLieu().getId());
            stmt.setBoolean(6, d.isEstDisponible());
            stmt.setInt(7, d.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_DISPONIBILITE, e);
        }
    }

    /**
     * Supprime une disponibilité existante.
     *
     * @param d La disponibilité à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur lors de l'accès à la base de données.
     */
    @Override
    public boolean delete(Disponibilite d) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM disponibilite WHERE id = ?");
            stmt.setInt(1, d.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_DISPONIBILITE, e);
        }
    }
}
