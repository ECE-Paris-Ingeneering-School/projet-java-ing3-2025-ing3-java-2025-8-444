package controller;

import dao.DAOFactory;
import exceptions.DaoOperationException;
import model.Patient;

import java.util.List;

public class PatientController {

    public boolean inscrirePatient(String nom, String prenom, String email, String motDePasse) {
        try {
            Patient patient = new Patient(0, nom, prenom, email, motDePasse);
            return DAOFactory.getPatientDAO().save(patient);
        } catch (DaoOperationException e) {
            System.err.println("Erreur lors de l'inscription du patient : " + e.getMessage());
            return false;
        }
    }

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
