package dao;

/* Sources :
https://drive.google.com/file/d/1mvTcyiJjID0ZQtiLQuOi2GhnBmaQY-aW/view
https://drive.google.com/file/d/1jYcbKG3FMyEa1OpaPpYy5Om3dF3RCXky/view
https://openclassrooms.com/fr/courses/2434016-developpez-des-sites-web-avec-java-ee/2438696-utiliser-le-modele-dao
*/

import model.Utilisateur;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * DAO générique pour l'entité {@link Utilisateur}.
 * <p>
 * Cette classe sert de base pour les DAO spécifiques (PatientDAO, AdminDAO, etc.).
 * Elle n'implémente pas directement les opérations CRUD classiques.
 */
public class UtilisateurDAO implements DAO<Utilisateur> {
    /**
     * Connexion à la base de données utilisée par le DAO.
     */
    protected Connection connection;

    /**
     * Constructeur initialisant la connexion.
     *
     * @param connection La connexion à utiliser.
     */
    public UtilisateurDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Cette méthode n'est pas implémentée pour {@link Utilisateur}.
     * Utiliser un DAO spécifique pour récupérer un utilisateur.
     *
     * @param id L'identifiant de l'utilisateur.
     * @return Ne retourne jamais.
     * @throws UnsupportedOperationException Toujours levée.
     */
    @Override
    public Utilisateur get(int id) {
        throw new UnsupportedOperationException("Méthode get() à implémenter selon le type concret de l'utilisateur");
    }

    /**
     * Cette méthode retourne une liste vide.
     * <p>
     * À surcharger dans les DAO spécifiques si besoin.
     *
     * @return Une liste vide.
     */
    @Override
    public List<Utilisateur> getAll() {
        return new ArrayList<>();
    }

    /**
     * Cette méthode n'est pas implémentée pour {@link Utilisateur}.
     * Utiliser un DAO spécifique pour sauvegarder un utilisateur.
     *
     * @param obj L'objet utilisateur.
     * @return Ne retourne jamais.
     * @throws UnsupportedOperationException Toujours levée.
     */
    @Override
    public boolean save(Utilisateur obj) {
        throw new UnsupportedOperationException("Utiliser un DAO spécifique (Patient, Admin, etc.)");
    }

    /**
     * Cette méthode n'est pas implémentée pour {@link Utilisateur}.
     * Utiliser un DAO spécifique pour mettre à jour un utilisateur.
     *
     * @param obj L'objet utilisateur.
     * @return Ne retourne jamais.
     * @throws UnsupportedOperationException Toujours levée.
     */
    @Override
    public boolean update(Utilisateur obj) {
        throw new UnsupportedOperationException("Utiliser un DAO spécifique (Patient, Admin, etc.)");
    }

    /**
     * Cette méthode n'est pas implémentée pour {@link Utilisateur}.
     * Utiliser un DAO spécifique pour supprimer un utilisateur.
     *
     * @param obj L'objet utilisateur.
     * @return Ne retourne jamais.
     * @throws UnsupportedOperationException Toujours levée.
     */
    @Override
    public boolean delete(Utilisateur obj) {
        throw new UnsupportedOperationException("Utiliser un DAO spécifique (Patient, Admin, etc.)");
    }

    /**
     * Compte le nombre total d'utilisateurs enregistrés dans la base de données.
     *
     * @return Le nombre total d'utilisateurs.
     */
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
