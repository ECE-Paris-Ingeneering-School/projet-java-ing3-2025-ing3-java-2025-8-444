package view;

import controller.AdminController;
import model.Specialite;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AjoutSpecialisteView extends JFrame {
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JTextField qualificationField;
    private JPasswordField mdpField;
    private JComboBox<String> specialiteCombo;
    private AdminController controller;

    public AjoutSpecialisteView() {
        controller = new AdminController();

        setTitle("Ajouter un nouveau spécialiste");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nomField = new JTextField();
        prenomField = new JTextField();
        emailField = new JTextField();
        mdpField = new JPasswordField();
        qualificationField = new JTextField();

        List<Specialite> specialites = controller.getToutesSpecialites();
        specialiteCombo = new JComboBox<>(specialites.stream().map(Specialite::getNom).toArray(String[]::new));

        panel.add(new JLabel("Nom :"));
        panel.add(nomField);
        panel.add(new JLabel("Prénom :"));
        panel.add(prenomField);
        panel.add(new JLabel("Email :"));
        panel.add(emailField);
        panel.add(new JLabel("Mot de passe :"));
        panel.add(mdpField);
        panel.add(new JLabel("Qualification :"));
        panel.add(qualificationField);
        panel.add(new JLabel("Spécialité :"));
        panel.add(specialiteCombo);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.addActionListener(e -> handleAjout());
        panel.add(ajouterButton);
        return panel;
    }

    private void handleAjout() {
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String email = emailField.getText().trim();
        String mdp = new String(mdpField.getPassword()).trim();
        String qualification = qualificationField.getText().trim();
        String specialite = (String) specialiteCombo.getSelectedItem();

        if (controller.ajouterSpecialiste(nom, prenom, email, mdp, qualification, specialite)) {
            JOptionPane.showMessageDialog(this, "Spécialiste ajouté avec succès !");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout. Vérifiez les données ou l'email déjà utilisé.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
