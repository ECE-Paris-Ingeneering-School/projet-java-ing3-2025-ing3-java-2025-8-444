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

public class AdminController {
    private final SpecialisteDAO specialisteDAO;
    private final SpecialiteDAO specialiteDAO;

    public AdminController() {
        this.specialisteDAO = DAOFactory.getSpecialisteDAO();
        this.specialiteDAO = DAOFactory.getSpecialiteDAO();
    }

    public List<Specialite> getToutesSpecialites() {
        try {
            return specialiteDAO.getAll();
        } catch (DaoOperationException e) {
            System.err.println("Erreur lors de la récupération des spécialités : " + e.getMessage());
            return List.of(); // Liste vide en cas d'erreur
        }
    }

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
