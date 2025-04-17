package view;

import dao.AdminDAO;
import dao.PatientDAO;
import dao.SpecialisteDAO;
import model.Admin;
import model.Patient;
import model.Specialiste;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GestionUtilisateursAdminView extends JFrame {

    private final DefaultListModel<Utilisateur> userModel;
    private final JList<Utilisateur> userList;
    private final PatientDAO patientDAO = new PatientDAO();
    private final AdminDAO adminDAO = new AdminDAO();
    private final SpecialisteDAO specialisteDAO = new SpecialisteDAO();

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

    private void chargerUtilisateurs() {
        userModel.clear();
        List<Utilisateur> all = new ArrayList<>();
        all.addAll(patientDAO.getAll());
        all.addAll(adminDAO.getAll());
        all.addAll(specialisteDAO.getAll());
        all.forEach(userModel::addElement);
    }

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
            if (u instanceof Patient) success = patientDAO.update((Patient) u);
            else if (u instanceof Specialiste) success = specialisteDAO.update((Specialiste) u);
            else if (u instanceof Admin) success = adminDAO.update((Admin) u);

            if (success) {
                JOptionPane.showMessageDialog(this, "Utilisateur modifié avec succès.");
                chargerUtilisateurs();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la modification.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void supprimerUtilisateur() {
        Utilisateur u = userList.getSelectedValue();
        if (u == null) return;

        int confirm = JOptionPane.showConfirmDialog(this, "Supprimer ce compte ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = false;
            if (u instanceof Patient) success = patientDAO.delete((Patient) u);
            else if (u instanceof Specialiste) success = specialisteDAO.delete((Specialiste) u);
            else if (u instanceof Admin) success = adminDAO.delete((Admin) u);

            if (success) {
                JOptionPane.showMessageDialog(this, "Compte supprimé.");
                chargerUtilisateurs();
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la suppression.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}