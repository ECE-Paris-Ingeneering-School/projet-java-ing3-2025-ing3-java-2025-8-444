package dao;

import model.Specialite;
import java.sql.*;
import java.util.*;
import util.DatabaseConnection;

public class SpecialiteDAO implements DAO<Specialite> {
    @Override
    public Specialite get(int id) {
        throw new UnsupportedOperationException("Utiliser getByName(String nom) à la place.");
    }

    public Specialite getByName(String nom) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM specialite WHERE nom = ?");
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Specialite(rs.getString("nom"), rs.getString("description"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Specialite> getAll() {
        List<Specialite> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM specialite");
            while (rs.next()) {
                list.add(new Specialite(rs.getString("nom"), rs.getString("description")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean save(Specialite s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO specialite (nom, description) VALUES (?, ?)");
            stmt.setString(1, s.getNom());
            stmt.setString(2, s.getDescription());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean update(Specialite s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE specialite SET description=? WHERE nom=?");
            stmt.setString(1, s.getDescription());
            stmt.setString(2, s.getNom());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean delete(Specialite s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM specialite WHERE nom=?");
            stmt.setString(1, s.getNom());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
