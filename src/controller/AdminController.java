package controller;

import dao.DAOFactory;
import dao.SpecialisteDAO;
import dao.SpecialiteDAO;
import exceptions.DaoOperationException;
import exceptions.SpecialiteNotFoundException;
import exceptions.SpecialisteSaveException;
import model.Specialiste;
import model.Specialite;

import java.util.List;

import static util.exceptionsConstantes.ERREUR_SAUVEGARDE_SPECIALISTE;
import static util.exceptionsConstantes.SPECIALITE_NON_TROUVEE;

/**
 * Contrôleur permettant de gérer les opérations administratives.
 */
public class AdminController {

    private final SpecialisteDAO specialisteDAO;
    private final SpecialiteDAO specialiteDAO;

    /**
     * Constructeur initialisant les DAO pour Specialiste et Specialite.
     */
    public AdminController() {
        this.specialisteDAO = DAOFactory.getSpecialisteDAO();
        this.specialiteDAO = DAOFactory.getSpecialiteDAO();
    }

    /**
     * Récupère la liste complète des spécialités disponibles.
     *
     * @return une liste de spécialités, vide si une erreur survient.
     */
    public List<Specialite> getToutesSpecialites() {
        try {
            return specialiteDAO.getAll();
        } catch (DaoOperationException e) {
            System.err.println("Erreur lors de la récupération des spécialités : " + e.getMessage());
            return List.of(); // Liste vide en cas d'erreur
        }
    }

    /**
     * Ajoute un nouveau spécialiste dans le système.
     *
     * @param nom           le nom du spécialiste.
     * @param prenom        le prénom du spécialiste.
     * @param email         l'email du spécialiste.
     * @param mdp           le mot de passe du spécialiste.
     * @param qualification la qualification du spécialiste.
     * @param specialiteNom le nom de la spécialité du spécialiste.
     * @return true si le spécialiste est ajouté avec succès, false sinon.
     */
    public boolean ajouterSpecialiste(String nom, String prenom, String email, String mdp, String qualification, String specialiteNom) {
        try {
            Specialite specialite = specialiteDAO.getByName(specialiteNom);
            if (specialite == null) {
                throw new SpecialiteNotFoundException(SPECIALITE_NON_TROUVEE);
            }

            Specialiste nouveau = new Specialiste(0, nom, prenom, email, mdp, qualification, specialite);
            boolean saved = specialisteDAO.save(nouveau);
            if (!saved) {
                throw new SpecialisteSaveException(ERREUR_SAUVEGARDE_SPECIALISTE);
            }

            return true;

        } catch (SpecialiteNotFoundException | SpecialisteSaveException | DaoOperationException e) {
            System.err.println("Erreur lors de l'ajout du spécialiste : " + e.getMessage());
            return false;
        }
    }
}
