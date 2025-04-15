package view;

import model.Utilisateur;
import javax.swing.*;
import java.awt.*;

public class AccueilSpecialisteView extends JFrame {

    public AccueilSpecialisteView(Utilisateur user) {
        setTitle("Espace Spécialiste - Bienvenue Dr " + user.getNom());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Espace Spécialiste : Gestion des disponibilités & rendez-vous.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));

        panel.add(label, BorderLayout.CENTER);
        add(panel);
    }
}
