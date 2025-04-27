package view;

import dao.RendezVousDAO;
import exceptions.DaoOperationException;
import model.RendezVous;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static util.exceptionsConstantes.*;

/**
 * Page admin de la gestion des rendez vous
 */
public class GestionRendezVousAdminView extends JFrame {

    private final DefaultListModel<RendezVous> rdvModel;
    private final JList<RendezVous> rdvList;
    private final RendezVousDAO rdvDAO;

    /**
     * Liste des rendez vous de la plateforme et panel de boutons pour les gérer
     */
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

    /**
     * Programme pour charger les rendez vous, fait appel à rdvDAO et récupère toute la table de rendez vous
     */
    private void chargerRendezVous() {
        rdvModel.clear();
        try {
            List<RendezVous> all = rdvDAO.getAll();
            for (RendezVous r : all) {
                rdvModel.addElement(r);
            }
        } catch (DaoOperationException e) {
            JOptionPane.showMessageDialog(this, ERREUR_CHARGEMENT_RDV + "\n" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Panel de boutons
     * Modifier le statut ou la note
     * Supprimer le rendez vous
     * Fermer la page
     * @return panel
     */
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

    /**
     * Programme modification rendez vous
     * Champ pour entrer un commentaire sur le rendez vous
     * Champ pour modifier le statut du rendez vous
     */
    private void modifierRdv() {
        RendezVous rdv = rdvList.getSelectedValue();
        if (rdv == null) return;

        String nouveauStatut = JOptionPane.showInputDialog(this, "Nouveau statut :", rdv.getStatut());
        String nouvellesNotes = JOptionPane.showInputDialog(this, "Notes :", rdv.getNotes());

        if (nouveauStatut != null && nouvellesNotes != null) {
            rdv.setStatut(nouveauStatut);
            rdv.setNotes(nouvellesNotes);
            try {
                if (rdvDAO.update(rdv)) {
                    JOptionPane.showMessageDialog(this, "Rendez-vous modifié.");
                    chargerRendezVous();
                } else {
                    JOptionPane.showMessageDialog(this, ERREUR_MISE_A_JOUR_RDV, "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DaoOperationException e) {
                JOptionPane.showMessageDialog(this, ERREUR_MISE_A_JOUR_RDV + "\n" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Programme supprimer rendez vous
     */
    private void supprimerRdv() {
        RendezVous rdv = rdvList.getSelectedValue();
        if (rdv == null) return;

        int confirm = JOptionPane.showConfirmDialog(this, "Confirmer la suppression de ce rendez-vous ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (rdvDAO.delete(rdv)) {
                    JOptionPane.showMessageDialog(this, "Rendez-vous supprimé.");
                    chargerRendezVous();
                } else {
                    JOptionPane.showMessageDialog(this, ERREUR_SUPPRESSION_RDV, "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DaoOperationException e) {
                JOptionPane.showMessageDialog(this, ERREUR_SUPPRESSION_RDV + "\n" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
