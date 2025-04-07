package vue;

import javax.swing.*;
import java.awt.*;

public class MesRendezVousGUI extends JFrame {
    private JTextArea rdvArea;
    private JButton retourButton;

    public MesRendezVousGUI() {
        setTitle("Mes Rendez-vous");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        rdvArea = new JTextArea();
        rdvArea.setEditable(false);
        retourButton = new JButton("Retour");

        setLayout(new BorderLayout());
        add(new JScrollPane(rdvArea), BorderLayout.CENTER);
        add(retourButton, BorderLayout.SOUTH);
    }

    // Getters pour le contrôleur
    public JTextArea getRdvArea() {
        return rdvArea;
    }

    public JButton getRetourButton() {
        return retourButton;
    }
}
