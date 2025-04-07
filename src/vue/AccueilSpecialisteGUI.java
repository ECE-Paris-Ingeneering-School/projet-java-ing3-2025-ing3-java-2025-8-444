package vue;

import javax.swing.*;
import java.awt.*;

public class AccueilSpecialisteGUI extends JFrame {
    private JButton voirRdvsButton;
    private JButton modifierDisponibilitesButton;
    private JButton deconnexionButton;

    public AccueilSpecialisteGUI() {
        setTitle("Accueil Spécialiste");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        voirRdvsButton = new JButton("Voir mes rendez-vous");
        modifierDisponibilitesButton = new JButton("Modifier disponibilités");
        deconnexionButton = new JButton("Se déconnecter");

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(voirRdvsButton);
        panel.add(modifierDisponibilitesButton);
        panel.add(deconnexionButton);

        add(panel);
    }

    // Getters pour le contrôleur
    public JButton getVoirRdvsButton() {
        return voirRdvsButton;
    }

    public JButton getModifierDisponibilitesButton() {
        return modifierDisponibilitesButton;
    }

    public JButton getDeconnexionButton() {
        return deconnexionButton;
    }
}
