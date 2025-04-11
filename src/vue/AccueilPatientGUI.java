package vue;

import javax.swing.*;
import java.awt.*;

public class AccueilPatientGUI extends JFrame {
    private JButton mesRdvsButton;
    private JButton prendreRdvsButton;
    private JButton deconnexionButton;

    public AccueilPatientGUI() {
        setTitle("Accueil Patient");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mesRdvsButton = new JButton("Mes rendez-vous");
        prendreRdvsButton = new JButton("Prendre un rendez-vous");
        deconnexionButton = new JButton("Se déconnecter");

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(mesRdvsButton);
        panel.add(prendreRdvsButton);
        panel.add(deconnexionButton);

        add(panel);
    }


    public JButton getMesRdvsButton() {
        return mesRdvsButton;
    }

    public JButton getPrendreRdvsButton() {
        return prendreRdvsButton;
    }

    public JButton getDeconnexionButton() {
        return deconnexionButton;
    }
}
