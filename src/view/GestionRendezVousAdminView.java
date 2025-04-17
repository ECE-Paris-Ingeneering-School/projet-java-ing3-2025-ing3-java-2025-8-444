package view;

import dao.RendezVousDAO;
import model.RendezVous;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestionRendezVousAdminView extends JFrame {

    private final DefaultListModel<RendezVous> rdvModel;
    private final JList<RendezVous> rdvList;
    private final RendezVousDAO rdvDAO;

    public GestionRendezVousAdminView() {
        rdvDAO = new RendezVousDAO();
        rdvModel = new DefaultListModel<>();
        rdvList = new JList<>(rdvModel);
        setTitle("Gestion des Rendez-vous - Admin");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new JScrollPane(rdvList), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        chargerRendezVous();
        setVisible(true);
    }

    private void chargerRendezVous() {
        rdvModel.clear();
        List<RendezVous> all = rdvDAO.getAll();
        for (RendezVous r : all) {
            rdvModel.addElement(r);
        }
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        JButton modifierBtn = new JButton("Modifier statut/notes");
        modifierBtn.addActionListener(e -> modifierRdv());

        JButton supprimerBtn = new JButton("Supprimer");
        supprimerBtn.addActionListener(e -> supprimerRdv());

        JButton fermerBtn = new JButton("Fermer");
        fermerBtn.addActionListener(e -> dispose());

        panel.add(modifierBtn);
        panel.add(supprimerBtn);
        panel.add(fermerBtn);

        return panel;
    }

    private void modifierRdv() {
        RendezVous rdv = rdvList.getSelectedValue();
        if (rdv == null) return;

        String nouveauStatut = JOptionPane.showInputDialog(this, "Nouveau statut :", rdv.getStatut());
        String nouvellesNotes = JOptionPane.showInputDialog(this, "Notes :", rdv.getNotes());

        if (nouveauStatut != null && nouvellesNotes != null) {
            rdv.setStatut(nouveauStatut);
            rdv.setNotes(nouvellesNotes);
            if (rdvDAO.update(rdv)) {
                JOptionPane.showMessageDialog(this, "Rendez-vous modifié.");
                chargerRendezVous();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void supprimerRdv() {
        RendezVous rdv = rdvList.getSelectedValue();
        if (rdv == null) return;

        int confirm = JOptionPane.showConfirmDialog(this, "Confirmer la suppression de ce rendez-vous ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (rdvDAO.delete(rdv)) {
                JOptionPane.showMessageDialog(this, "Rendez-vous supprimé.");
                chargerRendezVous();
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la suppression.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
