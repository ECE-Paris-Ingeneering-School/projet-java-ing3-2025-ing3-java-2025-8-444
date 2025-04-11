package vue;

import controleur.ConnexionController;
import javax.swing.*;
import java.awt.*;

public class ConnexionGUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton connexionButton;
    private JButton inscriptionButton;

    public ConnexionGUI() {
        setTitle("Connexion");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        connexionButton = new JButton("Se connecter");
        inscriptionButton = new JButton("Inscription Patient");

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.add(new JLabel("Email :"));
        panel.add(emailField);
        panel.add(new JLabel("Mot de passe :"));
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(connexionButton);
        panel.add(new JLabel());
        panel.add(inscriptionButton);

        add(panel);
    }


    public JButton getConnexionButton() {
        return connexionButton;
    }

    public JButton getInscriptionButton() {
        return inscriptionButton;
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getMotDePasse() {
        return new String(passwordField.getPassword());
    }

    // Pour démarrer l'interface via ce main (optionnel si tu préfères utiliser une classe Main séparée)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConnexionGUI gui = new ConnexionGUI();
            new ConnexionController(gui);
            gui.setVisible(true);
        });
    }
}
