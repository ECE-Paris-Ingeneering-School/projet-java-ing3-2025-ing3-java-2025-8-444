package dao;

import exceptions.DaoOperationException;
import model.RendezVous;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.exceptionsConstantes.ERREUR_DAO_RDV;

public class RendezVousDAO implements DAO<RendezVous> {
    private final PatientDAO patientDAO = new PatientDAO();
    private final SpecialisteDAO specialisteDAO = new SpecialisteDAO();
    private final DisponibiliteDAO disponibiliteDAO = new DisponibiliteDAO();

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
}
