package dao;

import modele.*;
import java.sql.*;
import java.util.*;

public class SpecialisteDAO {
    private Connection connection;

    public SpecialisteDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Specialiste> findAll() {
        List<Specialiste> specialistes = new ArrayList<>();
        String sql = "SELECT * FROM Specialiste";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                specialistes.add(new Specialiste(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("specialisation"),
                    rs.getString("qualification")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return specialistes;
    }

    public Specialiste findById(int id) {
        Specialiste s = null;
        String sql = "SELECT * FROM Specialiste WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                s = new Specialiste(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("specialisation"),
                    rs.getString("qualification")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
}