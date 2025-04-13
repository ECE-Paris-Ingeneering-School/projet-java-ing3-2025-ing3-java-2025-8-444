package dao;

import model.Lieu;
import java.sql.*;
import java.util.*;
import util.DatabaseConnection;

public class LieuDAO implements DAO<Lieu> {
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
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

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
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean save(Lieu l) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO lieu (nom, adresse, ville, code_postal) VALUES (?, ?, ?, ?)");
            stmt.setString(1, l.getNom());
            stmt.setString(2, l.getAdresse());
            stmt.setString(3, l.getVille());
            stmt.setString(4, l.getCodePostal());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

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
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean delete(Lieu l) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM lieu WHERE id=?");
            stmt.setInt(1, l.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}