package dao;

import modele.*;
import java.sql.*;
import java.util.*;


public class RendezVousDAO {
    private Connection connection;

    public RendezVousDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(RendezVous rdv) {
        String sql = "INSERT INTO RendezVous (patient_id, specialiste_id, disponibilite_id, note) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, rdv.getPatientId());
            stmt.setInt(2, rdv.getSpecialisteId());
            stmt.setInt(3, rdv.getDisponibiliteId());
            stmt.setString(4, rdv.getNote());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RendezVous> findByPatient(int patientId) {
        List<RendezVous> rdvs = new ArrayList<>();
        String sql = "SELECT * FROM RendezVous WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rdvs.add(new RendezVous(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    rs.getInt("specialiste_id"),
                    rs.getInt("disponibilite_id"),
                    rs.getString("note")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rdvs;
    }
}
