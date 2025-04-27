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
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);

        getContentPane().setBackground(new Color(245, 245, 245));
        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("Doc'n'Roll");
        logo.setFont(new Font("SansSerif", Font.BOLD, 26));
        logo.setForeground(Color.WHITE);

        JLabel title = new JLabel("Choisissez une spécialité pour rechercher un créneau disponible");
        title.setFont(new Font("SansSerif", Font.PLAIN, 18));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton accueilButton = new JButton("Accueil Patient");
        accueilButton.setBackground(Color.WHITE);
        accueilButton.addActionListener(e -> {
            dispose();
            new AccueilPatientView(user);
        });

        JButton logoutButton = new JButton("Déconnexion");
        logoutButton.setBackground(Color.WHITE);
        logoutButton.addActionListener(e -> {
            dispose();
            new AcceuilView();
        });

        buttonPanel.add(accueilButton);
        buttonPanel.add(logoutButton);

        header.add(logo, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        header.add(buttonPanel, BorderLayout.EAST);

        return header;
    }

    private JPanel createMainPanel() {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(245, 245, 245));

        JPanel content = new JPanel(new BorderLayout(20, 20));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        content.setPreferredSize(new Dimension(600, 400));

        specialiteCombo = new JComboBox<>(controller.getToutesLesSpecialites().toArray(new String[0]));
        specialiteCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        specialiteCombo.addActionListener(e -> chargerDisponibilites());

        dispoListModel = new DefaultListModel<>();
        dispoList = new JList<>(dispoListModel);
        dispoList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(dispoList);

        JButton prendreRDVButton = new JButton("Prendre ce rendez-vous");
        prendreRDVButton.setBackground(new Color(52, 152, 219));
        prendreRDVButton.setForeground(Color.WHITE);
        prendreRDVButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        prendreRDVButton.addActionListener(e -> reserverSelection());

        content.add(specialiteCombo, BorderLayout.NORTH);
        content.add(scrollPane, BorderLayout.CENTER);
        content.add(prendreRDVButton, BorderLayout.SOUTH);

        wrapper.add(content);
        return wrapper;
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
