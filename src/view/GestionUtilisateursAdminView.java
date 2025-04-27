package view;

import dao.AdminDAO;
import dao.PatientDAO;
import dao.SpecialisteDAO;
import exceptions.DaoOperationException;
import model.Admin;
import model.Patient;
import model.Specialiste;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static util.exceptionsConstantes.*;

/**
 * Page de gestion des utilisateurs
 * Uniquement accessible par l'admin
 */
public class GestionUtilisateursAdminView extends JFrame {

    private final DefaultListModel<Utilisateur> userModel;
    private final JList<Utilisateur> userList;
    private final PatientDAO patientDAO = new PatientDAO();
    private final AdminDAO adminDAO = new AdminDAO();
    private final SpecialisteDAO specialisteDAO = new SpecialisteDAO();

    /**
     * Page composée de la liste des utilisateurs et d'un panel de boutons pour les gérer
     */
    public GestionUtilisateursAdminView() {
        userModel = new DefaultListModel<>();
        userList = new JList<>(userModel);

        setTitle("Gestion des utilisateurs - Admin");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new JScrollPane(userList), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        chargerUtilisateurs();
        setVisible(true);
    }

    /**
     * Programme pour charger les utilisateurs
     * fait appel à PatientDAO, AdminDAO, SpecialisteDAO et les ajoute dans la liste
     */
    private void chargerUtilisateurs() {
        userModel.clear();
        try {
            List<Utilisateur> all = new ArrayList<>();
            all.addAll(patientDAO.getAll());
            all.addAll(adminDAO.getAll());
            all.addAll(specialisteDAO.getAll());
            all.forEach(userModel::addElement);
        } catch (DaoOperationException e) {
            JOptionPane.showMessageDialog(this, ERREUR_CHARGEMENT_UTILISATEUR + "\n" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Panel de boutons
     * Modifier pour modifier les infos de l'utilisateur
     * Supprimer pour supprimer l'utilisateur de la bdd
     * Fermer pour fermer la page
     * @return bouton
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        JButton modifier = new JButton("Modifier");
        modifier.addActionListener(e -> modifierUtilisateur());

        JButton supprimer = new JButton("Supprimer");
        supprimer.addActionListener(e -> supprimerUtilisateur());

        JButton fermer = new JButton("Fermer");
        fermer.addActionListener(e -> dispose());

        panel.add(modifier);
        panel.add(supprimer);
        panel.add(fermer);

        return panel;
    }

    /**
     * Programme pour modifier les Utilisateurs
     * Affiche les informations de l'utilisateurs dans des champs modifiables
     */
    private void modifierUtilisateur() {
        Utilisateur u = userList.getSelectedValue();
        if (u == null) return;

        String nom = JOptionPane.showInputDialog(this, "Nom :", u.getNom());
        String prenom = JOptionPane.showInputDialog(this, "Prénom :", u.getPrenom());
        String email = JOptionPane.showInputDialog(this, "Email :", u.getEmail());
        String mdp = JOptionPane.showInputDialog(this, "Mot de passe :", u.getMotDePasse());

        if (nom != null && prenom != null && email != null && mdp != null) {
            u.setNom(nom);
            u.setPrenom(prenom);
            u.setEmail(email);
            u.setMotDePasse(mdp);
            boolean success = false;
            try {
                if (u instanceof Patient) success = patientDAO.update((Patient) u);
                else if (u instanceof Specialiste) success = specialisteDAO.update((Specialiste) u);
                else if (u instanceof Admin) success = adminDAO.update((Admin) u);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Utilisateur modifié avec succès.");
                    chargerUtilisateurs();
                } else {
                    JOptionPane.showMessageDialog(this, ERREUR_MISE_A_JOUR_UTILISATEUR, "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DaoOperationException e) {
                JOptionPane.showMessageDialog(this, ERREUR_MISE_A_JOUR_UTILISATEUR + "\n" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Programme pour supprimer un utilisateur
     * Supprime l'utilisateur sélectionné
     */
    private void supprimerUtilisateur() {
        Utilisateur u = userList.getSelectedValue();
        if (u == null) return;

        int confirm = JOptionPane.showConfirmDialog(this, "Supprimer ce compte ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = false;
            try {
                if (u instanceof Patient) success = patientDAO.delete((Patient) u);
                else if (u instanceof Specialiste) success = specialisteDAO.delete((Specialiste) u);
                else if (u instanceof Admin) success = adminDAO.delete((Admin) u);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Compte supprimé.");
                    chargerUtilisateurs();
                } else {
                    JOptionPane.showMessageDialog(this, ERREUR_SUPPRESSION_UTILISATEUR, "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DaoOperationException e) {
                JOptionPane.showMessageDialog(this, ERREUR_SUPPRESSION_UTILISATEUR + "\n" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
