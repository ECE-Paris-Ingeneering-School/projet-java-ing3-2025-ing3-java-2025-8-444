package vue;

import javax.swing.*;
import java.awt.*;

public class PrendreRendezVousGUI extends JFrame {
    private JComboBox<String> specialisteCombo;
    private JList<String> disponibiliteList;
    private JButton prendreButton;
    private JButton retourButton;

    public PrendreRendezVousGUI() {
        setTitle("Prendre un rendez-vous");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Pour l'exemple, nous simulons une liste de spécialistes
        specialisteCombo = new JComboBox<>(new String[]{"Specialiste 1", "Specialiste 2", "Specialiste 3"});
        disponibiliteList = new JList<>(new DefaultListModel<>());
        prendreButton = new JButton("Prendre ce rendez-vous");
        retourButton = new JButton("Retour");

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Choisir un spécialiste :"), BorderLayout.WEST);
        topPanel.add(specialisteCombo, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JLabel("Créneaux disponibles :"), BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(disponibiliteList), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(prendreButton);
        bottomPanel.add(retourButton);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JComboBox<String> getSpecialisteCombo() {
        return specialisteCombo;
    }

    public JList<String> getDisponibiliteList() {
        return disponibiliteList;
    }

    public JButton getPrendreButton() {
        return prendreButton;
    }

    public JButton getRetourButton() {
        return retourButton;
    }
}
