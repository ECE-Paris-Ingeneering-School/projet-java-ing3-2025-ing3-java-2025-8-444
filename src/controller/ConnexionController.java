package controller;

import dao.*;
import exceptions.DaoOperationException;
import exceptions.UtilisateurNonTrouveException;
import model.*;

import static util.exceptionsConstantes.UTILISATEUR_NON_TROUVE;

public class ConnexionController {
    public Utilisateur seConnecter(String email, String motDePasse) {
        try {
            // Test Patient
            for (Patient p : DAOFactory.getPatientDAO().getAll()) {
                if (p.getEmail().equalsIgnoreCase(email) && p.getMotDePasse().equals(motDePasse)) {
                    return p;
                }
            }

            // Test Admin
            for (Admin a : DAOFactory.getAdminDAO().getAll()) {
                if (a.getEmail().equalsIgnoreCase(email) && a.getMotDePasse().equals(motDePasse)) {
                    return a;
                }
            }

            // Test Specialiste
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
