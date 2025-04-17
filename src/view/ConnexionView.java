package view;

import controller.ConnexionController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConnexionView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private ConnexionController controller;

    public ConnexionView() {
        controller = new ConnexionController();
        setTitle("Connexion - Doc'n'Roll");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createConnexionPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 140, 0));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("Doc'n'Roll");
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        logo.setForeground(Color.WHITE);

        JButton retour = new JButton("Retour à l'accueil");
        retour.setBackground(Color.WHITE);
        retour.addActionListener(e -> {
            dispose();
            new AcceuilView();
        });

        header.add(logo, BorderLayout.WEST);
        header.add(retour, BorderLayout.EAST);
        return header;
    }

    private JPanel createConnexionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.WHITE);

        JLabel emailLabel = new JLabel("Email :");
        JLabel passwordLabel = new JLabel("Mot de passe :");
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Connexion");
        JButton registerButton = new JButton("Créer un compte patient");

        loginButton.setBackground(new Color(255, 140, 0));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this::handleLogin);

        registerButton.setForeground(Color.BLUE);
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.PLAIN, 12));
        registerButton.addActionListener(e -> {
            dispose();
            new InscriptionView();
        });

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(loginButton, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(registerButton, gbc);

        return panel;
    }

    private void handleLogin(ActionEvent e) {
        String email = emailField.getText();
        String mdp = new String(passwordField.getPassword());

        Utilisateur user = controller.seConnecter(email, mdp);
        if (user != null) {
            dispose();

            if (user instanceof Patient) {
                new AccueilPatientView((Patient) user);
            } else if (user instanceof Admin) {
                new AccueilAdminView((Admin) user);
            } else if (user instanceof Specialiste) {
                new AccueilSpecialisteView((Specialiste) user);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Identifiants incorrects", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
