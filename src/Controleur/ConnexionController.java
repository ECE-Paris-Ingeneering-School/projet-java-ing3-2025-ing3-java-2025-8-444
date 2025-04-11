package controleur;

import dao.ConnexionBDD;
import dao.PatientDAO;
import modele.Patient;
import vue.ConnexionGUI;
import vue.AccueilPatientGUI;
import vue.InscriptionPatientGUI;

import javax.swing.*;
import java.sql.Connection;

public class ConnexionController {
    private ConnexionGUI connexionGUI;

    public ConnexionController(ConnexionGUI connexionGUI) {
        this.connexionGUI = connexionGUI;
        initController();
    }

    private void initController() {
        connexionGUI.getConnexionButton().addActionListener(e -> authenticate());
        connexionGUI.getInscriptionButton().addActionListener(e -> openInscription());
    }

    private void authenticate() {
        String email = connexionGUI.getEmail();
        String password = connexionGUI.getMotDePasse();
        Connection conn = ConnexionBDD.getConnection();
        PatientDAO patientDAO = new PatientDAO(conn);
        Patient patient = patientDAO.findByEmailAndPassword(email, password);
        if (patient != null) {
            JOptionPane.showMessageDialog(connexionGUI, "Connexion réussie !");
            new AccueilPatientGUI().setVisible(true);
            connexionGUI.dispose();
        } else {
            JOptionPane.showMessageDialog(connexionGUI, "Identifiants incorrects !");
        }
    }
    
    private void openInscription() {
        this.connexionGUI.dispose();
        InscriptionPatientGUI inscriptionGui = new InscriptionPatientGUI();
        new InscriptionPatientController(inscriptionGui); 
        inscriptionGui.setVisible(true);
    }
    
}
