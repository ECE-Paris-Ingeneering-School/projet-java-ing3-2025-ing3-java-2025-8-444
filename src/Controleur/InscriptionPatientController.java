package controleur;

import dao.ConnexionBDD;
import dao.PatientDAO;
import modele.Patient;
import vue.InscriptionPatientGUI;
import vue.ConnexionGUI;

import javax.swing.*;
import java.sql.Connection;

public class InscriptionPatientController {
    private InscriptionPatientGUI gui;
    private PatientDAO patientDAO;

    public InscriptionPatientController(InscriptionPatientGUI gui) {
        this.gui = gui;
        Connection conn = ConnexionBDD.getConnection();
        patientDAO = new PatientDAO(conn);
        initController();
    }

    private void initController() {
        gui.getInscrireButton().addActionListener(e -> inscrire());
    }

    private void inscrire() {
        String nom = gui.getNomField().getText();
        String prenom = gui.getPrenomField().getText();
        String email = gui.getEmailField().getText();
        String password = gui.getPassword();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(gui, "Tous les champs sont obligatoires");
            return;
        }
        Patient p = new Patient(0, nom, prenom, email, password, "Nouveau");
        boolean success = patientDAO.save(p); 
        if (success) {
            JOptionPane.showMessageDialog(gui, "Inscription réussie !");
            gui.dispose();
            new ConnexionGUI().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(gui, "Erreur lors de l'inscription !");
        }
    }
}
