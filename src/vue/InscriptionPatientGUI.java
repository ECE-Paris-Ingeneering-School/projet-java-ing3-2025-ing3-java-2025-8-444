package vue;

import javax.swing.*;
import java.awt.*;

public class InscriptionPatientGUI extends JFrame {
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton inscrireButton;

    public InscriptionPatientGUI() {
        setTitle("Inscription Patient");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        inscrireButton = new JButton("S'inscrire");

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Nom :"));
        panel.add(nomField);
        panel.add(new JLabel("Prénom :"));
        panel.add(prenomField);
        panel.add(new JLabel("Email :"));
        panel.add(emailField);
        panel.add(new JLabel("Mot de passe :"));
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(inscrireButton);

        add(panel);
    }

    // Getters pour le contrôleur
    public JTextField getNomField() {
        return nomField;
    }

    public JTextField getPrenomField() {
        return prenomField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getInscrireButton() {
        return inscrireButton;
    }
}
