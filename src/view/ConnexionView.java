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
        setTitle("Connexion");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Mot de passe:");

        emailField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Se connecter");
        loginButton.addActionListener(this::handleLogin);

        JButton registerButton = new JButton("S'inscrire");
        registerButton.addActionListener(e -> {
            dispose();  // ferme la fenêtre actuelle
            new InscriptionView();  // ouvre la fenêtre d'inscription
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());  // vide
        panel.add(loginButton);
        panel.add(new JLabel());  // vide
        panel.add(registerButton);

        add(panel);
    }

    private void handleLogin(ActionEvent e) {
        String email = emailField.getText();
        String mdp = new String(passwordField.getPassword());

        Utilisateur user = controller.seConnecter(email, mdp);
        if (user != null) {
            dispose();  // ferme la fenêtre Connexion

            if (user instanceof Admin) {
                new AccueilAdminView(user);
            } else if (user instanceof Specialiste) {
                new AccueilSpecialisteView(user);
            } else if (user instanceof Patient) {
                new AccueilPatientView(user);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Identifiants incorrects", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
