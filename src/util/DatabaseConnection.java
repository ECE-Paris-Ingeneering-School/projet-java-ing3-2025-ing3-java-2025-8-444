package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/rdv_specialiste";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Mets ton mot de passe ici si besoin

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Important pour certains environnements
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Pilote JDBC non trouvé !", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
