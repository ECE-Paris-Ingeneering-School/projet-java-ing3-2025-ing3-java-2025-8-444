package view;

/* Sources :
https://docs.google.com/document/d/1pqb7wHIJjQyNsmtiUEV_-4jRH8h9gBCJ/edit?tab=t.0
https://boostcamp.omneseducation.com/pluginfile.php/4818934/mod_resource/content/1/Creation_interface_graphique_avec_Swing.pdf
https://www.geeksforgeeks.org/introduction-to-java-swing/
https://stackoverflow.com/questions/tagged/java
https://examples.javacodegeeks.com/java-development/core-java/java-swing-mvc-example/
*/

import controller.DisponibiliteController;
import exceptions.DaoOperationException;
import exceptions.DisponibiliteExistanteException;
import exceptions.DisponibiliteSaveException;
import model.Lieu;
import model.Specialiste;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Page pour que le spécialiste ajoute une disponibilité.
 */
public class AjoutDisponibiliteView extends JFrame {
    private Specialiste specialiste;
    private DisponibiliteController controller;

    private JComboBox<Lieu> lieuCombo;
    private JTextField dateField;
    private JTextField debutField;
    private JTextField finField;

    /**
     * Page composée de champs à remplir et d'un bouton de confirmation
     * @param specialiste L'objet représentant un spécialiste pour lequel la disponibilité est ajoutée
     */
    public AjoutDisponibiliteView(Specialiste specialiste) {
        this.specialiste = specialiste;
        this.controller = new DisponibiliteController();

        setTitle("Ajouter une disponibilité - Dr " + specialiste.getNom());
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Form pour ajouter les informations de la dispo du spécialiste
     * Date, heure de début, heure de fin, et lieu
     * @return form
     */
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Date (AAAA-MM-JJ) :"));
        dateField = new JTextField();
        panel.add(dateField);

        panel.add(new JLabel("Heure début (HH:MM) :"));
        debutField = new JTextField();
        panel.add(debutField);

        panel.add(new JLabel("Heure fin (HH:MM) :"));
        finField = new JTextField();
        panel.add(finField);

        panel.add(new JLabel("Lieu :"));
        try {
            lieuCombo = new JComboBox<>(controller.getTousLieux().toArray(new Lieu[0]));
        } catch (DaoOperationException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des lieux : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            lieuCombo = new JComboBox<>();
        }
        panel.add(lieuCombo);

        return panel;
    }

    /**
     * Bouton de confirmation des informations remplies dans le form
     * @return bouton
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        JButton ajouterBtn = new JButton("Ajouter");
        ajouterBtn.addActionListener(e -> ajouterDisponibilite());
        panel.add(ajouterBtn);
        return panel;
    }

    /**
     * Programme pour ajouter une disponibilité, prend les infos renseignées dans le forme et les envoie
     * au controller DisponibiliteController
     */
    private void ajouterDisponibilite() {
        try {
            LocalDate date = LocalDate.parse(dateField.getText().trim());
            LocalTime debut = LocalTime.parse(debutField.getText().trim());
            LocalTime fin = LocalTime.parse(finField.getText().trim());
            Lieu lieu = (Lieu) lieuCombo.getSelectedItem();

            try {
                boolean success = controller.ajouterDisponibilite(specialiste, date, debut, fin, lieu);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Disponibilité ajoutée avec succès !");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur : créneau peut-être déjà pris ou invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DisponibiliteExistanteException | DisponibiliteSaveException | DaoOperationException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de la disponibilité : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Format de date ou d'heure invalide. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
