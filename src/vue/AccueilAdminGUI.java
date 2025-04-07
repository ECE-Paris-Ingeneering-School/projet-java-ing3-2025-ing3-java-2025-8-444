package vue;

import javax.swing.*;
import java.awt.*;

public class AccueilAdminGUI extends JFrame {
    private JButton gererSpecialistesButton;
    private JButton gererPatientsButton;
    private JButton voirStatsButton;
    private JButton deconnexionButton;

    public AccueilAdminGUI() {
        setTitle("Accueil Admin");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gererSpecialistesButton = new JButton("Gérer spécialistes");
        gererPatientsButton = new JButton("Gérer patients");
        voirStatsButton = new JButton("Voir statistiques");
        deconnexionButton = new JButton("Se déconnecter");

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(gererSpecialistesButton);
        panel.add(gererPatientsButton);
        panel.add(voirStatsButton);
        panel.add(deconnexionButton);

        add(panel);
    }

    // Getters pour le contrôleur
    public JButton getGererSpecialistesButton() {
        return gererSpecialistesButton;
    }

    public JButton getGererPatientsButton() {
        return gererPatientsButton;
    }

    public JButton getVoirStatsButton() {
        return voirStatsButton;
    }

    public JButton getDeconnexionButton() {
        return deconnexionButton;
    }
}
