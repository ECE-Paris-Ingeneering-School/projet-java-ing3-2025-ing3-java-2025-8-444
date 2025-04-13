package dao;

import model.Admin;
import util.DatabaseConnection;

import java.sql.*;
import java.util.*;

public class AdminDAO implements DAO<Admin> {
    @Override
    public Admin get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.* FROM utilisateur u JOIN admin a ON u.id = a.id WHERE u.id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Admin(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> admins = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.* FROM utilisateur u JOIN admin a ON u.id = a.id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                admins.add(new Admin(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return admins;
    }

    @Override
    public boolean save(Admin admin) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            String sqlUser = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, type) VALUES (?, ?, ?, ?, 'admin')";
            PreparedStatement stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            stmtUser.setString(1, admin.getNom());
            stmtUser.setString(2, admin.getPrenom());
            stmtUser.setString(3, admin.getEmail());
            stmtUser.setString(4, admin.getMotDePasse());
            stmtUser.executeUpdate();

            ResultSet generatedKeys = stmtUser.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                admin.setId(userId);

                String sqlAdmin = "INSERT INTO admin (id) VALUES (?)";
                PreparedStatement stmtAdmin = conn.prepareStatement(sqlAdmin);
                stmtAdmin.setInt(1, userId);
                stmtAdmin.executeUpdate();

                conn.commit();
                return true;
            }

            conn.rollback();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean update(Admin admin) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE utilisateur SET nom=?, prenom=?, email=?, mot_de_passe=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getNom());
            stmt.setString(2, admin.getPrenom());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getMotDePasse());
            stmt.setInt(5, admin.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean delete(Admin admin) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM utilisateur WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, admin.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
