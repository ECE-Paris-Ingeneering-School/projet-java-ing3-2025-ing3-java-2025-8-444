package view;

import controller.PatientController;
import model.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InscriptionView extends JFrame {
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private PatientController controller;

    public InscriptionView() {
        controller = new PatientController();
        setTitle("Inscription Patient");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 250);
        setLocationRelativeTo(null);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        nomField = new JTextField();
        prenomField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();

        JButton registerButton = new JButton("S'inscrire");
        registerButton.addActionListener(this::handleRegister);

        panel.add(new JLabel("Nom:"));
        panel.add(nomField);
        panel.add(new JLabel("Prénom:"));
        panel.add(prenomField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Mot de passe:"));
        panel.add(passwordField);
        panel.add(new JLabel());  // vide
        panel.add(registerButton);

        add(panel);
    }

    private void handleRegister(ActionEvent e) {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String mdp = new String(passwordField.getPassword());

        if (controller.inscrirePatient(nom, prenom, email, mdp)) {
            JOptionPane.showMessageDialog(this, "Inscription réussie !");
            dispose();  // ferme l'inscription
            new ConnexionView();  // retour à la connexion
        } else {
            JOptionPane.showMessageDialog(this, "Erreur : Email déjà utilisé ou problème d'inscription.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
