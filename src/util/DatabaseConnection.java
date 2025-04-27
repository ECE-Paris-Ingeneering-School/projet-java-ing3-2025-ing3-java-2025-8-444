package util;

/* Sources
https://www.jmdoudoux.fr/java/dej/chap-jdbc.htm
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitaire permettant de gérer la connexion à la base de données MySQL
 */

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/rdv_specialiste";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Constructeur par défaut.
     * Initialise sans configuration particulière.
     */
    public DatabaseConnection() {
        // Rien de spécifique à initialiser
    }
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Pilote JDBC non trouvé !", e);
        }
    }

    /**
     * Obtient une connexion active à la base de données
     *
     * @return Une instance {@link Connection} connectée à la base de données
     * @throws SQLException Si une erreur survient lors de la connexion
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
