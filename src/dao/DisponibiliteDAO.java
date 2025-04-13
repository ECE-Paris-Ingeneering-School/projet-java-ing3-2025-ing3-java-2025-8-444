package dao;

import model.*;
import util.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class DisponibiliteDAO implements DAO<Disponibilite> {
    private final LieuDAO lieuDAO = new LieuDAO();
    private final SpecialisteDAO specialisteDAO = new SpecialisteDAO();

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
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

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
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean save(Disponibilite d) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO disponibilite (specialiste_id, date, heure_debut, heure_fin, lieu_id, est_disponible) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, d.getSpecialiste().getId());
            stmt.setDate(2, java.sql.Date.valueOf(d.getDate())); // Spécifier java.sql.Date explicitement
            stmt.setTime(3, java.sql.Time.valueOf(d.getHeureDebut()));
            stmt.setTime(4, java.sql.Time.valueOf(d.getHeureFin()));
            stmt.setInt(5, d.getLieu().getId());
            stmt.setBoolean(6, d.isEstDisponible());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean update(Disponibilite d) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE disponibilite SET specialiste_id=?, date=?, heure_debut=?, heure_fin=?, lieu_id=?, est_disponible=? WHERE id=?");
            stmt.setInt(1, d.getSpecialiste().getId());
            stmt.setDate(2, java.sql.Date.valueOf(d.getDate())); // Spécifier java.sql.Date explicitement
            stmt.setTime(3, java.sql.Time.valueOf(d.getHeureDebut()));
            stmt.setTime(4, java.sql.Time.valueOf(d.getHeureFin()));
            stmt.setInt(5, d.getLieu().getId());
            stmt.setBoolean(6, d.isEstDisponible());
            stmt.setInt(7, d.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean delete(Disponibilite d) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM disponibilite WHERE id = ?");
            stmt.setInt(1, d.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
