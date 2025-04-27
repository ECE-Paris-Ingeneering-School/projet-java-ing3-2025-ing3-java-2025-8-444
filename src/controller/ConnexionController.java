package controller;

import dao.*;
import exceptions.DaoOperationException;
import exceptions.UtilisateurNonTrouveException;
import model.*;

import static util.exceptionsConstantes.UTILISATEUR_NON_TROUVE;

/**
 * Contrôleur gérant la connexion des utilisateurs.
 */
public class ConnexionController {

    /**
     * Connecte un utilisateur selon son email et son mot de passe.
     * Cherche parmi les patients, administrateurs et spécialistes.
     *
     * @param email L'email de l'utilisateur.
     * @param motDePasse Le mot de passe de l'utilisateur.
     * @return L'utilisateur correspondant si trouvé, sinon null.
     */
    public Utilisateur seConnecter(String email, String motDePasse) {
        try {
            // Vérification des patients
            for (Patient p : DAOFactory.getPatientDAO().getAll()) {
                if (p.getEmail().equalsIgnoreCase(email) && p.getMotDePasse().equals(motDePasse)) {
                    return p;
                }
            }

            // Vérification des administrateurs
            for (Admin a : DAOFactory.getAdminDAO().getAll()) {
                if (a.getEmail().equalsIgnoreCase(email) && a.getMotDePasse().equals(motDePasse)) {
                    return a;
                }
            }

            // Vérification des spécialistes
            for (Specialiste s : DAOFactory.getSpecialisteDAO().getAll()) {
                if (s.getEmail().equalsIgnoreCase(email) && s.getMotDePasse().equals(motDePasse)) {
                    return s;
                }
            }

            throw new UtilisateurNonTrouveException(UTILISATEUR_NON_TROUVE);

        } catch (UtilisateurNonTrouveException | DaoOperationException e) {
            System.err.println("Erreur lors de la connexion : " + e.getMessage());
            return null;
        }
    }
}
