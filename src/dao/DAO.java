package dao;

import java.util.List;

/**
 * Interface générique définissant les opérations de base pour un DAO (Data Access Object).
 *
 * @param <T> Le type d'objet manipulé par le DAO.
 */
public interface DAO<T> {

    /**
     * Récupère un objet par son identifiant.
     *
     * @param id L'identifiant de l'objet.
     * @return L'objet correspondant ou {@code null} s'il n'existe pas.
     */
    T get(int id);

    /**
     * Récupère tous les objets.
     *
     * @return Une liste contenant tous les objets.
     */
    List<T> getAll();

    /**
     * Sauvegarde un nouvel objet.
     *
     * @param obj L'objet à sauvegarder.
     * @return {@code true} si la sauvegarde a réussi, {@code false} sinon.
     */
    boolean save(T obj);

    /**
     * Met à jour un objet existant.
     *
     * @param obj L'objet avec les données mises à jour.
     * @return {@code true} si la mise à jour a réussi, {@code false} sinon.
     */
    boolean update(T obj);

    /**
     * Supprime un objet existant.
     *
     * @param obj L'objet à supprimer.
     * @return {@code true} si la suppression a réussi, {@code false} sinon.
     */
    boolean delete(T obj);
}
