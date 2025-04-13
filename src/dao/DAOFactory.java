package dao;

import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory {
    private static Connection connection;

    static {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données: " + e.getMessage());
            // En production, utilisez un logger à la place de System.err
        }
    }

    public static UtilisateurDAO getUtilisateurDAO() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DatabaseConnection.getConnection();
            }
            return new UtilisateurDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création du DAO Utilisateur", e);
        }
    }

    public static PatientDAO getPatientDAO() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DatabaseConnection.getConnection();
            }
            return new PatientDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création du DAO Patient", e);
        }
    }

    public static AdminDAO getAdminDAO() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DatabaseConnection.getConnection();
            }
            return new AdminDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création du DAO Admin", e);
        }
    }

    public static SpecialisteDAO getSpecialisteDAO() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DatabaseConnection.getConnection();
            }
            return new SpecialisteDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création du DAO Specialiste", e);
        }
    }

    public static LieuDAO getLieuDAO() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DatabaseConnection.getConnection();
            }
            return new LieuDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création du DAO Lieu", e);
        }
    }

    public static SpecialiteDAO getSpecialiteDAO() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DatabaseConnection.getConnection();
            }
            return new SpecialiteDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création du DAO Specialite", e);
        }
    }

    public static DisponibiliteDAO getDisponibiliteDAO() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DatabaseConnection.getConnection();
            }
            return new DisponibiliteDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création du DAO Disponibilite", e);
        }
    }

    public static RendezVousDAO getRendezVousDAO() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DatabaseConnection.getConnection();
            }
            return new RendezVousDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création du DAO RendezVous", e);
        }
    }
}