package view;

import controller.PriseRdvController;
import model.Disponibilite;
import model.Specialiste;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RechercheEtPriseRDVView extends JFrame {
    private Utilisateur user;
    private JComboBox<String> specialiteCombo;
    private DefaultListModel<String> dispoListModel;
    private JList<String> dispoList;
    private List<Disponibilite> disponibilites;
    private PriseRdvController controller;

    public RechercheEtPriseRDVView(Utilisateur user) {
        this.user = user;
        this.controller = new PriseRdvController();

        setTitle("Prendre un Rendez-vous - Doc'n'Roll");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(231, 141, 82));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("Doc'n'Roll");
        logo.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.add(logo, BorderLayout.WEST);

        JLabel title = new JLabel("Choisissez une spécialité pour rechercher un créneau disponible");
        title.setFont(new Font("SansSerif", Font.PLAIN, 16));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton accueilButton = new JButton("Accueil Patient");
        accueilButton.addActionListener(e -> {
            dispose();
            new AccueilPatientView(user);
        });
        JButton logoutButton = new JButton("Déconnexion");
        logoutButton.addActionListener(e -> {
            dispose();
            new ConnexionView();
        });
        buttonPanel.add(accueilButton);
        buttonPanel.add(logoutButton);
        header.add(buttonPanel, BorderLayout.EAST);

        return header;
    }

    private JPanel createMainPanel() {
        JPanel main = new JPanel(new BorderLayout());

        specialiteCombo = new JComboBox<>(controller.getToutesLesSpecialites().toArray(new String[0]));
        specialiteCombo.addActionListener(e -> chargerDisponibilites());
        main.add(specialiteCombo, BorderLayout.NORTH);

        dispoListModel = new DefaultListModel<>();
        dispoList = new JList<>(dispoListModel);
        JScrollPane scrollPane = new JScrollPane(dispoList);
        main.add(scrollPane, BorderLayout.CENTER);

        JButton prendreRDVButton = new JButton("Prendre ce RDV");
        prendreRDVButton.addActionListener(e -> reserverSelection());
        main.add(prendreRDVButton, BorderLayout.SOUTH);

        return main;
    }

    private void chargerDisponibilites() {
        String specialite = (String) specialiteCombo.getSelectedItem();
        disponibilites = controller.getDisponibilitesParSpecialite(specialite);
        dispoListModel.clear();
        for (Disponibilite d : disponibilites) {
            Specialiste s = d.getSpecialiste();
            dispoListModel.addElement(d.getDate() + " " + d.getHeureDebut() + " - " +
                    d.getHeureFin() + " | " + s.getNom() + " " + s.getPrenom() + " | " +
                    d.getLieu().getNom());
        }
    }

    private void reserverSelection() {
        int index = dispoList.getSelectedIndex();
        if (index >= 0) {
            Disponibilite dispoChoisie = disponibilites.get(index);
            boolean success = controller.reserverDispo(user, dispoChoisie);
            if (success) {
                JOptionPane.showMessageDialog(this, "Rendez-vous réservé avec succès !");
                chargerDisponibilites();
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la réservation.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un créneau.");
        }
    }
}
