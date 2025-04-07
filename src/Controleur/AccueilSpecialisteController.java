package controleur;

import vue.AccueilSpecialisteGUI;
import vue.ConnexionGUI;

import javax.swing.*;

public class AccueilSpecialisteController {
    private AccueilSpecialisteGUI gui;

    public AccueilSpecialisteController(AccueilSpecialisteGUI gui) {
        this.gui = gui;
        initController();
    }

    private void initController() {
        gui.getVoirRdvsButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(gui, "Affichage des rendez-vous (à implémenter)");
        });
        gui.getModifierDisponibilitesButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(gui, "Modification des disponibilités (à implémenter)");
        });
        gui.getDeconnexionButton().addActionListener(e -> {
            gui.dispose();
            new ConnexionGUI().setVisible(true);
        });
    }
}
