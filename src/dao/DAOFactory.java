package dao;

import exceptions.DaoCreationException;
import exceptions.DatabaseConnectionException;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

import static util.exceptionsConstantes.ERREUR_CONNEXION_BDD;
import static util.exceptionsConstantes.ERREUR_CREATION_DAO;

public class DAOFactory {
    private static Connection connection;

    static {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new DatabaseConnectionException(ERREUR_CONNEXION_BDD, e);
        }
    }

    private static void checkConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getConnection();
        }
    }

    public static UtilisateurDAO getUtilisateurDAO() {
        try {
            checkConnection();
            return new UtilisateurDAO(connection);
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO, e);
        }
    }

    public static PatientDAO getPatientDAO() {
        try {
            checkConnection();
            return new PatientDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO + " [PatientDAO]", e);
        }
    }

    public static AdminDAO getAdminDAO() {
        try {
            checkConnection();
            return new AdminDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO + " [AdminDAO]", e);
        }
    }


    public static SpecialisteDAO getSpecialisteDAO() {
        try {
            checkConnection();
            return new SpecialisteDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO + " [SpecialisteDAO]", e);
        }
    }


    public static LieuDAO getLieuDAO() {
        try {
            checkConnection();
            return new LieuDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO+ " [LieuDAO]", e);
        }
    }

    public static SpecialiteDAO getSpecialiteDAO() {
        try {
            checkConnection();
            return new SpecialiteDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO+ " [SpecialiteDAO]", e);
        }
    }

    public static DisponibiliteDAO getDisponibiliteDAO() {
        try {
            checkConnection();
            return new DisponibiliteDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO+ " [DiqponibilitéDAO]", e);
        }
    }

    public static RendezVousDAO getRendezVousDAO() {
        try {
            checkConnection();
            return new RendezVousDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO+ " [rendezVousDAO]", e);
        }
    }
}
