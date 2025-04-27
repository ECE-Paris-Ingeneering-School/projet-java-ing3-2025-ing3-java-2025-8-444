package dao;

import model.Utilisateur;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UtilisateurDAO implements DAO<Utilisateur> {
    protected Connection connection;

    public UtilisateurDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Utilisateur get(int id) {
        throw new UnsupportedOperationException("Méthode get() à implémenter selon le type concret de l'utilisateur");
    }

    @Override
    public List<Utilisateur> getAll() {
        return new ArrayList<>();
    }

    @Override
    public boolean save(Utilisateur obj) {
        throw new UnsupportedOperationException("Utiliser un DAO spécifique (Patient, Admin, etc.)");
    }

    @Override
    public boolean update(Utilisateur obj) {
        throw new UnsupportedOperationException("Utiliser un DAO spécifique (Patient, Admin, etc.)");
    }

    @Override
    public boolean delete(Utilisateur obj) {
        throw new UnsupportedOperationException("Utiliser un DAO spécifique (Patient, Admin, etc.)");
    }

    public int countUtilisateur() {
        int count = 0;


        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM utilisateur";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}