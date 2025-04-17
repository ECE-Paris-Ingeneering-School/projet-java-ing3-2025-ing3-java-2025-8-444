package view;

import controller.DisponibiliteController;
import model.Lieu;
import model.Specialiste;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AjoutDisponibiliteView extends JFrame {
    private Specialiste specialiste;
    private DisponibiliteController controller;

    private JComboBox<Lieu> lieuCombo;
    private JTextField dateField;
    private JTextField debutField;
    private JTextField finField;

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
        lieuCombo = new JComboBox<>(controller.getTousLieux().toArray(new Lieu[0]));
        panel.add(lieuCombo);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        JButton ajouterBtn = new JButton("Ajouter");
        ajouterBtn.addActionListener(e -> ajouterDisponibilite());
        panel.add(ajouterBtn);
        return panel;
    }

    private void ajouterDisponibilite() {
        try {
            LocalDate date = LocalDate.parse(dateField.getText().trim());
            LocalTime debut = LocalTime.parse(debutField.getText().trim());
            LocalTime fin = LocalTime.parse(finField.getText().trim());
            Lieu lieu = (Lieu) lieuCombo.getSelectedItem();

            boolean success = controller.ajouterDisponibilite(specialiste, date, debut, fin, lieu);
            if (success) {
                JOptionPane.showMessageDialog(this, "Disponibilité ajoutée avec succès !");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur : créneau peut-être déjà pris ou invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Format invalide. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
