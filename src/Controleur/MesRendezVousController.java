package controleur;

import dao.ConnexionBDD;
import dao.RendezVousDAO;
import modele.RendezVous;
import vue.MesRendezVousGUI;
import vue.AccueilPatientGUI;
import java.sql.Connection;
import java.util.List;

public class MesRendezVousController {
    private MesRendezVousGUI gui;
    private RendezVousDAO rdvDAO;

    public MesRendezVousController(MesRendezVousGUI gui) {
        this.gui = gui;
        Connection conn = ConnexionBDD.getConnection();
        rdvDAO = new RendezVousDAO(conn);
        initController();
        loadRendezVous();
    }

    private void initController() {
        gui.getRetourButton().addActionListener(e -> {
            gui.dispose();
            new AccueilPatientGUI().setVisible(true);
        });
    }

    private void loadRendezVous() {
        // Ici, nous simulons l'ID patient = 1.
        List<RendezVous> rdvs = rdvDAO.findByPatient(1);
        StringBuilder sb = new StringBuilder();
        for (RendezVous rdv : rdvs) {
            sb.append("Rendez-vous ID : ").append(rdv.getId())
              .append(", Specialiste ID : ").append(rdv.getSpecialisteId())
              .append(", Disponibilité ID : ").append(rdv.getDisponibiliteId())
              .append("\nNote : ").append(rdv.getNote())
              .append("\n\n");
        }
        gui.getRdvArea().setText(sb.toString());
    }
}
