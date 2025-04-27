package dao;

import exceptions.DaoOperationException;
import model.Specialiste;
import model.Specialite;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.exceptionsConstantes.ERREUR_DAO_SPECIALISTE;

public class SpecialisteDAO implements DAO<Specialiste> {

    @Override
    public Specialiste get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.nom, u.prenom, u.email, u.mot_de_passe, s.qualification, sp.nom AS spec_nom, sp.description " +
                    "FROM specialiste s " +
                    "JOIN utilisateur u ON s.id = u.id " +
                    "JOIN specialite sp ON s.specialite_nom = sp.nom " +
                    "WHERE s.id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Specialite specialite = new Specialite(rs.getString("spec_nom"), rs.getString("description"));
                return new Specialiste(
                        id,
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("qualification"),
                        specialite
                );
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALISTE, e);
        }
        return null;
    }

    @Override
    public List<Specialiste> getAll() {
        List<Specialiste> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT u.id, u.nom, u.prenom, u.email, u.mot_de_passe, s.qualification, sp.nom AS spec_nom, sp.description " +
                    "FROM specialiste s " +
                    "JOIN utilisateur u ON s.id = u.id " +
                    "JOIN specialite sp ON s.specialite_nom = sp.nom";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Specialite specialite = new Specialite(rs.getString("spec_nom"), rs.getString("description"));
                list.add(new Specialiste(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("qualification"),
                        specialite
                ));
            }
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALISTE, e);
        }
        return list;
    }

    @Override
    public boolean save(Specialiste s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            String sqlUser = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, type) VALUES (?, ?, ?, ?, 'specialiste')";
            PreparedStatement stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            stmtUser.setString(1, s.getNom());
            stmtUser.setString(2, s.getPrenom());
            stmtUser.setString(3, s.getEmail());
            stmtUser.setString(4, s.getMotDePasse());
            stmtUser.executeUpdate();

            ResultSet generatedKeys = stmtUser.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                s.setId(userId);

                String sqlSpecialiste = "INSERT INTO specialiste (id, qualification, specialite_nom) VALUES (?, ?, ?)";
                PreparedStatement stmtSpec = conn.prepareStatement(sqlSpecialiste);
                stmtSpec.setInt(1, userId);
                stmtSpec.setString(2, s.getQualification());
                stmtSpec.setString(3, s.getSpecialite().getNom());
                stmtSpec.executeUpdate();

                conn.commit();
                return true;
            }
            conn.rollback();
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALISTE, e);
        }
        return false;
    }

    @Override
    public boolean update(Specialiste s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE utilisateur u JOIN specialiste s ON u.id = s.id " +
                    "SET u.nom=?, u.prenom=?, u.email=?, u.mot_de_passe=?, s.qualification=?, s.specialite_nom=? WHERE u.id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, s.getNom());
            stmt.setString(2, s.getPrenom());
            stmt.setString(3, s.getEmail());
            stmt.setString(4, s.getMotDePasse());
            stmt.setString(5, s.getQualification());
            stmt.setString(6, s.getSpecialite().getNom());
            stmt.setInt(7, s.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALISTE, e);
        }
    }

    @Override
    public boolean delete(Specialiste s) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM utilisateur WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, s.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoOperationException(ERREUR_DAO_SPECIALISTE, e);
        }
    }
}
