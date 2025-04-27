package view;

import controller.ConnexionController;
import exceptions.DaoOperationException;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static util.exceptionsConstantes.IDENTIFIANTS_INCORRECTS;

/**
 * Page de connexion
 */
public class ConnexionView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private ConnexionController controller;

    /**
     * Page de connexion composée d'un header et d'un main
     */
    public ConnexionView() {
        controller = new ConnexionController();
        setTitle("Connexion - Doc'n'Roll");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Header, bleu, logo à gauche et bouton retour à droite
     * @return header
     */
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("Doc'n'Roll");
        logo.setFont(new Font("SansSerif", Font.BOLD, 26));
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

    /**
     * Main, blanc
     * Un champ email et un champ mot de passe à remplir
     * Bouton de confirmation
     * Bouton pour création d'un compte
     * @return main
     */
    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245)); // gris très clair
        panel.setLayout(new GridBagLayout());

        JPanel formWrapper = new JPanel(new GridBagLayout());
        formWrapper.setBackground(Color.WHITE);
        formWrapper.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titre = new JLabel("Connectez-vous à votre compte");
        titre.setFont(new Font("SansSerif", Font.BOLD, 22));
        titre.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel emailLabel = new JLabel("Email :");
        JLabel passwordLabel = new JLabel("Mot de passe :");
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Connexion");
        loginButton.setBackground(new Color(52, 152, 219));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this::handleLogin);

        JButton registerButton = new JButton("Créer un compte patient");
        registerButton.setForeground(Color.BLUE);
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.PLAIN, 12));
        registerButton.addActionListener(e -> {
            dispose();
            new InscriptionView();
        });

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        formWrapper.add(titre, gbc);

        gbc.gridwidth = 1; gbc.gridy++;
        formWrapper.add(emailLabel, gbc);
        gbc.gridx = 1;
        formWrapper.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formWrapper.add(passwordLabel, gbc);
        gbc.gridx = 1;
        formWrapper.add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy++;
        formWrapper.add(loginButton, gbc);

        gbc.gridy++;
        formWrapper.add(registerButton, gbc);

        panel.add(formWrapper);
        return panel;
    }
    /**
     * Programme qui assure la connexion
     * Appelle le controller ConnexionController et utilise sa fonction seConnecter
     */
    private void handleLogin(ActionEvent e) {
        String email = emailField.getText();
        String mdp = new String(passwordField.getPassword());

        try {
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
                JOptionPane.showMessageDialog(this, IDENTIFIANTS_INCORRECTS, "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (DaoOperationException ex) {
            JOptionPane.showMessageDialog(this, "Erreur de connexion à la base de données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur inattendue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
