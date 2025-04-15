package controller;

import dao.DAOFactory;
import model.Patient;

public class PatientController {

    public boolean inscrirePatient(String nom, String prenom, String email, String motDePasse) {
        Patient patient = new Patient(0, nom, prenom, email, motDePasse);
        return DAOFactory.getPatientDAO().save(patient);
    }

    public Patient getPatientByEmail(String email) {
        return DAOFactory.getPatientDAO().getAll()
                .stream()
                .filter(p -> p.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}
