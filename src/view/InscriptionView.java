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
        setTitle("Inscription - Doc'n'Roll");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createFormPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 140, 0));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("Doc'n'Roll");
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        logo.setForeground(Color.WHITE);

        JButton retour = new JButton("Retour à la connexion");
        retour.setBackground(Color.WHITE);
        retour.addActionListener(e -> {
            dispose();
            new ConnexionView();
        });

        header.add(logo, BorderLayout.WEST);
        header.add(retour, BorderLayout.EAST);
        return header;
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.WHITE);

        JLabel nomLabel = new JLabel("Nom :");
        JLabel prenomLabel = new JLabel("Prénom :");
        JLabel emailLabel = new JLabel("Email :");
        JLabel mdpLabel = new JLabel("Mot de passe :");

        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton registerButton = new JButton("S'inscrire");
        registerButton.setBackground(new Color(255, 140, 0));
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(this::handleRegister);

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(nomLabel, gbc);
        gbc.gridx = 1;
        panel.add(nomField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(prenomLabel, gbc);
        gbc.gridx = 1;
        panel.add(prenomField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(mdpLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(registerButton, gbc);

        return panel;
    }

    private void handleRegister(ActionEvent e) {
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String email = emailField.getText().trim();
        String mdp = new String(passwordField.getPassword()).trim();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Champs manquants", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer une adresse email valide.", "Email invalide", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (controller.inscrirePatient(nom, prenom, email, mdp)) {
            JOptionPane.showMessageDialog(this, "Inscription réussie ! Bienvenue " + prenom + " !");
            dispose();
            // Connexion directe au tableau de bord patient
            Patient newPatient = controller.getPatientByEmail(email);
            new AccueilPatientView(newPatient);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur : Email déjà utilisé ou problème d'inscription.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
