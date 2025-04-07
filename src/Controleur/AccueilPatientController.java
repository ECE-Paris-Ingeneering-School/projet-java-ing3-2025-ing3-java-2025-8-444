package controleur;

import vue.AccueilPatientGUI;
import vue.ConnexionGUI;
import vue.MesRendezVousGUI;
import vue.PrendreRendezVousGUI;

//import javax.swing.*;

public class AccueilPatientController {
    private AccueilPatientGUI gui;

    public AccueilPatientController(AccueilPatientGUI gui) {
        this.gui = gui;
        initController();
    }

    private void initController() {
        gui.getMesRdvsButton().addActionListener(e -> openMesRdv());
        gui.getPrendreRdvsButton().addActionListener(e -> openPrendreRdv());
        gui.getDeconnexionButton().addActionListener(e -> deconnect());
    }

    private void openMesRdv() {
        gui.dispose();
        new MesRendezVousGUI().setVisible(true);
    }

    private void openPrendreRdv() {
        gui.dispose();
        new PrendreRendezVousGUI().setVisible(true);
    }

    private void deconnect() {
        gui.dispose();
        new ConnexionGUI().setVisible(true);
    }
}
