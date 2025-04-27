package view;

/* Sources :
https://docs.google.com/document/d/1pqb7wHIJjQyNsmtiUEV_-4jRH8h9gBCJ/edit?tab=t.0
https://boostcamp.omneseducation.com/pluginfile.php/4818934/mod_resource/content/1/Creation_interface_graphique_avec_Swing.pdf
https://www.geeksforgeeks.org/introduction-to-java-swing/
https://stackoverflow.com/questions/tagged/java
https://examples.javacodegeeks.com/java-development/core-java/java-swing-mvc-example/
*/

import controller.AdminController;
import exceptions.DaoOperationException;
import exceptions.SpecialiteNonSelectionneeException;
import exceptions.SpecialiteNotFoundException;
import exceptions.SpecialisteSaveException;
import model.Specialite;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static util.exceptionsConstantes.SPECIALITE_NON_SELECTIONNEE;

/**
 * Page pour ajouter un spécialiste, uniquement accessible par l'admin
 */
public class AjoutSpecialisteView extends JFrame {
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JTextField qualificationField;
    private JPasswordField mdpField;
    private JComboBox<String> specialiteCombo;
    private AdminController controller;

    /**
     * Form à remplir avec les infos du spécialiste et bouton de confirmation
     */
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

    /**
     * Form à remplir avec les infos d'un nouveau spécialiste :
     * Nom, Prénom, Mail, Mot de passe, Qualification, Spécialité
     * @return form
     */
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nomField = new JTextField();
        prenomField = new JTextField();
        emailField = new JTextField();
        mdpField = new JPasswordField();
        qualificationField = new JTextField();

        try {
            List<Specialite> specialites = controller.getToutesSpecialites();
            specialiteCombo = new JComboBox<>(specialites.stream().map(Specialite::getNom).toArray(String[]::new));
        } catch (DaoOperationException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des spécialités : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            specialiteCombo = new JComboBox<>();
        }

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

    /**
     * Bouton de confirmation pour envoyer au programme suivant
     * @return bouton
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.addActionListener(e -> handleAjout());
        panel.add(ajouterButton);
        return panel;
    }

    /**
     * Programme pour ajouter le nouveau spécialiste, envoie les infos du spécialiste
     * au controller AdminController
     */
    private void handleAjout() {
        try {
            String nom = nomField.getText().trim();
            String prenom = prenomField.getText().trim();
            String email = emailField.getText().trim();
            String mdp = new String(mdpField.getPassword()).trim();
            String qualification = qualificationField.getText().trim();
            String specialite = (String) specialiteCombo.getSelectedItem();

            if (specialite == null || specialite.isEmpty()) {
                throw new SpecialiteNonSelectionneeException(SPECIALITE_NON_SELECTIONNEE);
            }

            boolean success = controller.ajouterSpecialiste(nom, prenom, email, mdp, qualification, specialite);
            if (success) {
                JOptionPane.showMessageDialog(this, "Spécialiste ajouté avec succès !");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout. Vérifiez les données ou l'email déjà utilisé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SpecialiteNonSelectionneeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SpecialiteNotFoundException | SpecialisteSaveException | DaoOperationException e) {
            JOptionPane.showMessageDialog(this, "Erreur technique lors de l'ajout du spécialiste : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
