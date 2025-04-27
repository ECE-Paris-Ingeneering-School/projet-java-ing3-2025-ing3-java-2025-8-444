package dao;

import exceptions.DaoOperationException;
import model.RendezVous;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.exceptionsConstantes.ERREUR_DAO_RDV;

/**
 * DAO (Data Access Object) pour la gestion des rendez-vous dans la base de données.
 * Implémente les opérations CRUD pour les objets {@link RendezVous}.
 */
public class RendezVousDAO implements DAO<RendezVous> {
    private final PatientDAO patientDAO = new PatientDAO();
    private final SpecialisteDAO specialisteDAO = new SpecialisteDAO();
    private final DisponibiliteDAO disponibiliteDAO = new DisponibiliteDAO();

    /**
     * Récupère un rendez-vous par son identifiant.
     *
     * @param id L'identifiant du rendez-vous.
     * @return L'objet {@link RendezVous} correspondant ou {@code null} s'il n'existe pas.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public RendezVous get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM rendezvous WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new RendezVous(
                        rs.getInt("id"),
                        patientDAO.get(rs.getInt("patient_id")),
                        specialisteDAO.get(rs.getInt("specialiste_id")),
                        disponibiliteDAO.get(rs.getInt("disponibilite_id")),
                        rs.getString("statut"),
                        rs.getString("notes")
                );
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_RDV, e);
        }
        return null;
    }

    /**
     * Récupère tous les rendez-vous.
     *
     * @return Une liste de tous les rendez-vous.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public List<RendezVous> getAll() {
        List<RendezVous> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rendezvous");
            while (rs.next()) {
                list.add(new RendezVous(
                        rs.getInt("id"),
                        patientDAO.get(rs.getInt("patient_id")),
                        specialisteDAO.get(rs.getInt("specialiste_id")),
                        disponibiliteDAO.get(rs.getInt("disponibilite_id")),
                        rs.getString("statut"),
                        rs.getString("notes")
                ));
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_RDV, e);
        }
        return list;
    }

    /**
     * Récupère tous les rendez-vous pour un patient donné, sauf ceux annulés.
     *
     * @param patientId L'identifiant du patient.
     * @return Une liste des rendez-vous du patient.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    public List<RendezVous> getAllForPatient(int patientId) {
        List<RendezVous> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM rendezvous WHERE patient_id = ? AND statut != 'annulé'");
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new RendezVous(
                        rs.getInt("id"),
                        patientDAO.get(rs.getInt("patient_id")),
                        specialisteDAO.get(rs.getInt("specialiste_id")),
                        disponibiliteDAO.get(rs.getInt("disponibilite_id")),
                        rs.getString("statut"),
                        rs.getString("notes")
                ));
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_RDV, e);
        }
        return list;
    }

    /**
     * Annule un rendez-vous en mettant à jour son statut.
     *
     * @param rdv Le rendez-vous à annuler.
     * @return {@code true} si l'annulation a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    public boolean annuler(RendezVous rdv) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE rendezvous SET statut = ? WHERE id = ?");
            stmt.setString(1, "annulé");
            stmt.setInt(2, rdv.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_RDV, e);
        }
    }

    /**
     * Sauvegarde un nouveau rendez-vous dans la base de données.
     *
     * @param rdv Le rendez-vous à sauvegarder.
     * @return {@code true} si l'insertion a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public boolean save(RendezVous rdv) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO rendezvous (patient_id, specialiste_id, disponibilite_id, statut, notes) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, rdv.getPatient().getId());
            stmt.setInt(2, rdv.getSpecialiste().getId());
            stmt.setInt(3, rdv.getDisponibilite().getId());
            stmt.setString(4, rdv.getStatut());
            stmt.setString(5, rdv.getNotes());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_RDV, e);
        }
    }

    /**
     * Met à jour un rendez-vous existant.
     *
     * @param rdv Le rendez-vous avec les informations mises à jour.
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public boolean update(RendezVous rdv) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE rendezvous SET patient_id=?, specialiste_id=?, disponibilite_id=?, statut=?, notes=? WHERE id=?"
            );
            stmt.setInt(1, rdv.getPatient().getId());
            stmt.setInt(2, rdv.getSpecialiste().getId());
            stmt.setInt(3, rdv.getDisponibilite().getId());
            stmt.setString(4, rdv.getStatut());
            stmt.setString(5, rdv.getNotes());
            stmt.setInt(6, rdv.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_RDV, e);
        }
    }

    /**
     * Supprime un rendez-vous de la base de données.
     *
     * @param rdv Le rendez-vous à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     * @throws DaoOperationException En cas d'erreur d'accès à la base de données.
     */
    @Override
    public boolean delete(RendezVous rdv) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM rendezvous WHERE id = ?");
            stmt.setInt(1, rdv.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_RDV, e);
        }
    }

    public boolean updateStatut(RendezVous rdv) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE RendezVous SET statut = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, rdv.getStatut());
            ps.setInt(2, rdv.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
