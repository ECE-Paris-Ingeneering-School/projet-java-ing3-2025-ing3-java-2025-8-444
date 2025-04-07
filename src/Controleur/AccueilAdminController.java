package controleur;

import vue.AccueilAdminGUI;
import vue.ConnexionGUI;

import javax.swing.*;

public class AccueilAdminController {
    private AccueilAdminGUI gui;

    public AccueilAdminController(AccueilAdminGUI gui) {
        this.gui = gui;
        initController();
    }

    private void initController() {
        gui.getGererSpecialistesButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(gui, "Gestion des spécialistes (à implémenter)");
        });
        gui.getGererPatientsButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(gui, "Gestion des patients (à implémenter)");
        });
        gui.getVoirStatsButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(gui, "Affichage des statistiques (à implémenter)");
        });
        gui.getDeconnexionButton().addActionListener(e -> {
            gui.dispose();
            new ConnexionGUI().setVisible(true);
        });
    }
}
