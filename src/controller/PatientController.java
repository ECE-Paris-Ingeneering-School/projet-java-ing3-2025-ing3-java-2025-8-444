package controller;

import dao.DAOFactory;
import exceptions.DaoOperationException;
import model.Patient;

import java.util.List;

/**
 * Contrôleur pour gérer les opérations liées aux patients.
 */
public class PatientController {

    /**
     * Inscrit un nouveau patient dans le système.
     *
     * @param nom Le nom du patient.
     * @param prenom Le prénom du patient.
     * @param email L'email du patient.
     * @param motDePasse Le mot de passe du patient.
     * @return true si l'inscription réussit, false sinon.
     */
    public boolean inscrirePatient(String nom, String prenom, String email, String motDePasse) {
        try {
            Patient patient = new Patient(0, nom, prenom, email, motDePasse);
            return DAOFactory.getPatientDAO().save(patient);
        } catch (DaoOperationException e) {
            System.err.println("Erreur lors de l'inscription du patient : " + e.getMessage());
            return false;
        }
    }

    /**
     * Recherche un patient par son email.
     *
     * @param email L'email du patient recherché.
     * @return Le patient trouvé, ou null si aucun patient ne correspond.
     */
    public Patient getPatientByEmail(String email) {
        try {
            List<Patient> patients = DAOFactory.getPatientDAO().getAll();
            return patients.stream()
                    .filter(p -> p.getEmail().equalsIgnoreCase(email))
                    .findFirst()
                    .orElse(null);
        } catch (DaoOperationException e) {
            System.err.println("Erreur lors de la recherche du patient : " + e.getMessage());
            return null;
        }
    }
}
