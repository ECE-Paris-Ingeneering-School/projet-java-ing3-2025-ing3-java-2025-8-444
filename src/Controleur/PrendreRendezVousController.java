package controleur;

import dao.ConnexionBDD;
import dao.DisponibiliteDAO;
import dao.RendezVousDAO;
import modele.Disponibilite;
import modele.RendezVous;
import vue.PrendreRendezVousGUI;
import vue.AccueilPatientGUI;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class PrendreRendezVousController {
    private PrendreRendezVousGUI gui;
    private DisponibiliteDAO dispoDAO;
    private RendezVousDAO rdvDAO;

    public PrendreRendezVousController(PrendreRendezVousGUI gui) {
        this.gui = gui;
        Connection conn = ConnexionBDD.getConnection();
        dispoDAO = new DisponibiliteDAO(conn);
        rdvDAO = new RendezVousDAO(conn);
        initController();
        loadDisponibilites();
    }

    private void initController() {
        gui.getPrendreButton().addActionListener(e -> reserver());
        gui.getRetourButton().addActionListener(e -> {
            gui.dispose();
            new AccueilPatientGUI().setVisible(true);
        });
        gui.getSpecialisteCombo().addActionListener(e -> loadDisponibilites());
    }

    private void loadDisponibilites() {
        // Pour cet exemple, on suppose que l'index du combobox + 1 correspond à l'ID du spécialiste
        int specialisteId = gui.getSpecialisteCombo().getSelectedIndex() + 1;
        List<Disponibilite> dispoList = dispoDAO.findDisponiblesBySpecialiste(specialisteId);
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Disponibilite d : dispoList) {
            model.addElement(d.getHoraire().toString() + " (ID:" + d.getId() + ")");
        }
        gui.getDisponibiliteList().setModel(model);
    }

    private void reserver() {
        int index = gui.getDisponibiliteList().getSelectedIndex();
        if (index < 0) {
            JOptionPane.showMessageDialog(gui, "Veuillez sélectionner un créneau");
            return;
        }
        int specialisteId = gui.getSpecialisteCombo().getSelectedIndex() + 1;
        List<Disponibilite> dispoList = dispoDAO.findDisponiblesBySpecialiste(specialisteId);
        Disponibilite selected = dispoList.get(index);

        RendezVous rdv = new RendezVous(0, 1, specialisteId, selected.getId(), "Rendez-vous réservé via l'application");
        rdvDAO.save(rdv);
        dispoDAO.updateDisponibilite(selected.getId(), false);
        JOptionPane.showMessageDialog(gui, "Rendez-vous réservé !");
        gui.dispose();
        new AccueilPatientGUI().setVisible(true);
    }
}
