package controller;

import dao.*;
import model.*;

public class ConnexionController {
    public Utilisateur seConnecter(String email, String motDePasse) {
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

        return null; // Aucun utilisateur trouvé
    }
}
