package view;

import controller.PatientController;
import model.Patient;
import controller.Mail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InscriptionView extends JFrame {
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private PatientController controller;
    private Mail mail;

    public InscriptionView() {
        controller = new PatientController();
        setTitle("Inscription - Doc'n'Roll");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.mail = new Mail();

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);

        getContentPane().setBackground(new Color(245, 245, 245));
        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("Doc'n'Roll");
        logo.setFont(new Font("SansSerif", Font.BOLD, 26));
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

    private JPanel createMainPanel() {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(245, 245, 245));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titre = new JLabel("Créer un compte patient");
        titre.setFont(new Font("SansSerif", Font.BOLD, 24));
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JLabel nomLabel = new JLabel("Nom :");
        JLabel prenomLabel = new JLabel("Prénom :");
        JLabel emailLabel = new JLabel("Email :");
        JLabel mdpLabel = new JLabel("Mot de passe :");

        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton registerButton = new JButton("S'inscrire");
        registerButton.setBackground(new Color(52, 152, 219));
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(this::handleRegister);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        formPanel.add(titre, gbc);

        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        formPanel.add(nomLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nomField, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        formPanel.add(prenomLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(prenomField, gbc);

        gbc.gridy = 3; gbc.gridx = 0;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridy = 4; gbc.gridx = 0;
        formPanel.add(mdpLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridy = 5; gbc.gridx = 1;
        formPanel.add(registerButton, gbc);

        wrapper.add(formPanel);
        return wrapper;
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
            Patient newPatient = controller.getPatientByEmail(email);
            new AccueilPatientView(newPatient);
            mail.envoimail(newPatient, "Bienvenue sur Doc N Roll " + prenom + " !\nL'équipe Doc N Roll ");
        } else {
            JOptionPane.showMessageDialog(this, "Erreur : Email déjà utilisé ou problème d'inscription.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
