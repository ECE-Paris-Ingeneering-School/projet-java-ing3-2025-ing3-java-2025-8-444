package dao;

/* Sources :
https://drive.google.com/file/d/1mvTcyiJjID0ZQtiLQuOi2GhnBmaQY-aW/view
https://drive.google.com/file/d/1jYcbKG3FMyEa1OpaPpYy5Om3dF3RCXky/view
https://openclassrooms.com/fr/courses/2434016-developpez-des-sites-web-avec-java-ee/2438696-utiliser-le-modele-dao
*/

import exceptions.DaoCreationException;
import exceptions.DatabaseConnectionException;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

import static util.exceptionsConstantes.ERREUR_CONNEXION_BDD;
import static util.exceptionsConstantes.ERREUR_CREATION_DAO;

/**
 * Fabrique de DAO (Data Access Object).
 * Fournit des instances de différentes classes DAO tout en gérant la connexion à la base de données.
 */
public class DAOFactory {
    private static Connection connection;

    /**
     * Constructeur par défaut.
     * Initialise le DAO sans configuration particulière.
     */
    public DAOFactory() {
        // Rien de spécifique à initialiser
    }

    static {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new DatabaseConnectionException(ERREUR_CONNEXION_BDD, e);
        }
    }

    /**
     * Vérifie si la connexion est valide, sinon tente de la rétablir.
     *
     * @throws SQLException si une erreur survient lors de la vérification ou la récupération de la connexion.
     */
    private static void checkConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getConnection();
        }
    }

    /**
     * Retourne une instance de {@link UtilisateurDAO}.
     *
     * @return une instance de {@link UtilisateurDAO}.
     * @throws DaoCreationException si une erreur se produit lors de la création.
     */
    public static UtilisateurDAO getUtilisateurDAO() {
        try {
            checkConnection();
            return new UtilisateurDAO(connection);
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO, e);
        }
    }

    /**
     * Retourne une instance de {@link PatientDAO}.
     *
     * @return une instance de {@link PatientDAO}.
     * @throws DaoCreationException si une erreur se produit lors de la création.
     */
    public static PatientDAO getPatientDAO() {
        try {
            checkConnection();
            return new PatientDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO + " [PatientDAO]", e);
        }
    }

    /**
     * Retourne une instance de {@link AdminDAO}.
     *
     * @return une instance de {@link AdminDAO}.
     * @throws DaoCreationException si une erreur se produit lors de la création.
     */
    public static AdminDAO getAdminDAO() {
        try {
            checkConnection();
            return new AdminDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO + " [AdminDAO]", e);
        }
    }

    /**
     * Retourne une instance de {@link SpecialisteDAO}.
     *
     * @return une instance de {@link SpecialisteDAO}.
     * @throws DaoCreationException si une erreur se produit lors de la création.
     */
    public static SpecialisteDAO getSpecialisteDAO() {
        try {
            checkConnection();
            return new SpecialisteDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO + " [SpecialisteDAO]", e);
        }
    }

    /**
     * Retourne une instance de {@link LieuDAO}.
     *
     * @return une instance de {@link LieuDAO}.
     * @throws DaoCreationException si une erreur se produit lors de la création.
     */
    public static LieuDAO getLieuDAO() {
        try {
            checkConnection();
            return new LieuDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO+ " [LieuDAO]", e);
        }
    }

    /**
     * Retourne une instance de {@link SpecialiteDAO}.
     *
     * @return une instance de {@link SpecialiteDAO}.
     * @throws DaoCreationException si une erreur se produit lors de la création.
     */
    public static SpecialiteDAO getSpecialiteDAO() {
        try {
            checkConnection();
            return new SpecialiteDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO+ " [SpecialiteDAO]", e);
        }
    }

    /**
     * Retourne une instance de {@link DisponibiliteDAO}.
     *
     * @return une instance de {@link DisponibiliteDAO}.
     * @throws DaoCreationException si une erreur se produit lors de la création.
     */
    public static DisponibiliteDAO getDisponibiliteDAO() {
        try {
            checkConnection();
            return new DisponibiliteDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO+ " [DiqponibilitéDAO]", e);
        }
    }

    /**
     * Retourne une instance de {@link RendezVousDAO}.
     *
     * @return une instance de {@link RendezVousDAO}.
     * @throws DaoCreationException si une erreur se produit lors de la création.
     */
    public static RendezVousDAO getRendezVousDAO() {
        try {
            checkConnection();
            return new RendezVousDAO();
        } catch (SQLException e) {
            throw new DaoCreationException(ERREUR_CREATION_DAO+ " [rendezVousDAO]", e);
        }
    }
}
