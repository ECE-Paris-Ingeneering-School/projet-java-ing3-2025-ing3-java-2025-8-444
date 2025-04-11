package dao;

import modele.*;


import java.sql.*;
import java.util.*;


public class DisponibiliteDAO {
    private Connection connection;

    public DisponibiliteDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Disponibilite> findDisponiblesBySpecialiste(int specialisteId) {
        List<Disponibilite> dispo = new ArrayList<>();
        String sql = "SELECT * FROM Disponibilite WHERE specialiste_id = ? AND disponible = TRUE";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, specialisteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dispo.add(new Disponibilite(
                    rs.getInt("id"),
                    rs.getInt("specialiste_id"),
                    rs.getTimestamp("horaire").toLocalDateTime(), 
                    rs.getBoolean("disponible")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dispo;
    }
    


    public void updateDisponibilite(int id, boolean dispo) {
        String sql = "UPDATE Disponibilite SET disponible = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, dispo);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}