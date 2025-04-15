package controller;

import dao.DAOFactory;
import model.Patient;

public class PatientController {

    public boolean inscrirePatient(String nom, String prenom, String email, String motDePasse) {
        Patient patient = new Patient(0, nom, prenom, email, motDePasse);
        return DAOFactory.getPatientDAO().save(patient);

    }
}
