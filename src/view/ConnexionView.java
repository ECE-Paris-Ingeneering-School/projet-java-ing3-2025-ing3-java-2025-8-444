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
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Mot de passe:");

        emailField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Se connecter");
        loginButton.addActionListener(this::handleLogin);

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);
    }

    private void handleLogin(ActionEvent e) {
        String email = emailField.getText();
        String mdp = new String(passwordField.getPassword());

        Utilisateur user = controller.seConnecter(email, mdp);
        if (user != null) {
            String role = user instanceof Admin ? "Admin"
                    : user instanceof Patient ? "Patient"
                    : "Spécialiste";

            JOptionPane.showMessageDialog(this, "Bienvenue " + user.getNom() + " ! (" + role + ")");
            // TODO : ouvrir la fenêtre correspondante au type d'utilisateur
        } else {
            JOptionPane.showMessageDialog(this, "Identifiants incorrects", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
