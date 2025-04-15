package view;

import model.Utilisateur;
import javax.swing.*;
import java.awt.*;

public class AccueilAdminView extends JFrame {

    public AccueilAdminView(Utilisateur user) {
        setTitle("Espace Admin - Bienvenue " + user.getNom());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Espace Administrateur : gestion globale du système.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));

        panel.add(label, BorderLayout.CENTER);
        add(panel);
    }
}
