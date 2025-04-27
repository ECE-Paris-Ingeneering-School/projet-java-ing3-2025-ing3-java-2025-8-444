package view;

/* Sources :
https://docs.google.com/document/d/1pqb7wHIJjQyNsmtiUEV_-4jRH8h9gBCJ/edit?tab=t.0
https://boostcamp.omneseducation.com/pluginfile.php/4818934/mod_resource/content/1/Creation_interface_graphique_avec_Swing.pdf
https://www.geeksforgeeks.org/introduction-to-java-swing/
https://stackoverflow.com/questions/tagged/java
https://examples.javacodegeeks.com/java-development/core-java/java-swing-mvc-example/
*/

import controller.PriseRdvController;
import exceptions.DaoOperationException;
import model.Disponibilite;
import model.Specialiste;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static util.exceptionsConstantes.*;

/**
 * Page de prise de rendez vous, accessible après avoir cliqué sur le bouton "ajouter un rendez vous"
 * sur la page d'accueil patient
 */
public class RechercheEtPriseRDVView extends JFrame {
    private Utilisateur user;
    private JComboBox<String> specialiteCombo;
    private DefaultListModel<String> dispoListModel;
    private JList<String> dispoList;
    private List<Disponibilite> disponibilites;
    private PriseRdvController controller;

    /**
     * Composée d'un header et un main panel
     * @param user On envoie l'utilisateur
     */
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

    /**
     * Header, bleu,
     * Logo à gauche, description de la page au milieu, bouton accueil patient et déconnexion à droite
     * @return
     */
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

    /**
     * Main panel, blanc
     * Menu déroulant des différentes spécialités, et liste des rendez vous disponibles pour ces spécialités
     * Bouton pour prendre le rendez vous
     * @return main
     */
    private JPanel createMainPanel() {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(245, 245, 245));

        JPanel content = new JPanel(new BorderLayout(20, 20));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        content.setPreferredSize(new Dimension(600, 400));

        try {
            specialiteCombo = new JComboBox<>(controller.getToutesLesSpecialites().toArray(new String[0]));
            specialiteCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
            specialiteCombo.addActionListener(e -> chargerDisponibilites());
        } catch (DaoOperationException e) {
            JOptionPane.showMessageDialog(this, ERREUR_CHARGEMENT_SPECIALITES + "\n" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            specialiteCombo = new JComboBox<>();
        }

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

    /**
     * Programme pour charger les disponibilités
     * fait appel au controller DisponibiliteController pour renvoyer les informations des rendez vous
     */
    private void chargerDisponibilites() {
        try {
            String specialite = (String) specialiteCombo.getSelectedItem();
            disponibilites = controller.getDisponibilitesParSpecialite(specialite);
            dispoListModel.clear();
            for (Disponibilite d : disponibilites) {
                Specialiste s = d.getSpecialiste();
                dispoListModel.addElement(d.getDate() + " " + d.getHeureDebut() + " - " +
                        d.getHeureFin() + " | " + s.getNom() + " " + s.getPrenom() + " | " +
                        d.getLieu().getNom());
            }
        } catch (DaoOperationException e) {
            JOptionPane.showMessageDialog(this, ERREUR_CHARGEMENT_DISPOS + "\n" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Programme de confirmation de la reservation, activé par le bouton de réservation
     */
    private void reserverSelection() {
        int index = dispoList.getSelectedIndex();
        if (index >= 0) {
            try {
                Disponibilite dispoChoisie = disponibilites.get(index);
                boolean success = controller.reserverDispo(user, dispoChoisie);
                if (success) {
                    JOptionPane.showMessageDialog(this, MESSAGE_RESERVATION_REUSSIE);
                    chargerDisponibilites();
                } else {
                    JOptionPane.showMessageDialog(this, ERREUR_RESERVATION_RDV, "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DaoOperationException e) {
                JOptionPane.showMessageDialog(this, ERREUR_RESERVATION_RDV + "\n" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, MESSAGE_SELECTION_CRENEAU);
        }
    }
}
