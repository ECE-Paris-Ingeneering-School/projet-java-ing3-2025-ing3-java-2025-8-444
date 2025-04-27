package view;

import controller.PriseRdvController;
import model.Disponibilite;
import model.Patient;
import model.Specialiste;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Page de résultat, affichée après avoir effectué une recherche dans la barre de recherche de la
 * page d'accueil
 */
public class ResultatsRechercheView extends JFrame {

    private Utilisateur user;
    private PriseRdvController controller = new PriseRdvController();

    /**
     * Composée d'un Header et d'un main panel (resultats)
     * @param resultats
     * @param user
     */
    public ResultatsRechercheView(List<Disponibilite> resultats, Utilisateur user) {
        this.user = user;

        setTitle("Résultats de la recherche - Doc'n'Roll");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createResultPanel(resultats), BorderLayout.CENTER);

        getContentPane().setBackground(new Color(245, 245, 245));
        setVisible(true);
    }

    /**
     * Header, bleu
     * Message au milieu, bouton retour à droite
     * @return header
     */
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.setBackground(new Color(52, 152, 219));

        JLabel title = new JLabel("Créneaux disponibles trouvés");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        panel.add(title, BorderLayout.CENTER);

        JButton fermer = new JButton("Retour");
        fermer.addActionListener(e -> {
            dispose();
            new AcceuilView(user);
        });

        panel.add(fermer, BorderLayout.EAST);

        return panel;
    }

    /**
     * Main panel, blanc
     * Liste des disponibilités contenants l'information recherchée dans la barre de recherche
     * Chaque disponibilité a un bouton pour reserver cette disponibilité
     * @param resultats
     * @return main
     */
    private JScrollPane createResultPanel(List<Disponibilite> resultats) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245));

        for (Disponibilite d : resultats) {
            Specialiste s = d.getSpecialiste();

            JPanel card = new JPanel();
            card.setLayout(new BorderLayout(10, 10));
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10),
                    BorderFactory.createLineBorder(new Color(200, 200, 200))
            ));
            card.setMaximumSize(new Dimension(900, 120));

            JPanel infosPanel = new JPanel(new GridLayout(3, 1));
            infosPanel.setOpaque(false);

            JLabel titre = new JLabel("Dr " + s.getPrenom() + " " + s.getNom() + " - " + s.getSpecialite().getNom());
            titre.setFont(new Font("SansSerif", Font.BOLD, 16));

            JLabel infos = new JLabel("Lieu : " + d.getLieu().getNom() + ", " + d.getLieu().getVille());
            JLabel horaire = new JLabel("Date : " + d.getDate() + " | " + d.getHeureDebut() + " - " + d.getHeureFin());

            infosPanel.add(titre);
            infosPanel.add(infos);
            infosPanel.add(horaire);

            JButton reserverBtn = new JButton("Réserver ce créneau");
            reserverBtn.setBackground(new Color(52, 152, 219));
            reserverBtn.setForeground(Color.WHITE);
            reserverBtn.addActionListener(e -> reserverDispo(d));

            card.add(infosPanel, BorderLayout.CENTER);
            card.add(reserverBtn, BorderLayout.EAST);

            panel.add(card);
            panel.add(Box.createVerticalStrut(10));
        }

        return new JScrollPane(panel);
    }

    /**
     * Programme de reservation de la disponibilité sélectionnée
     * fait appel au controller disponibiliteController
     * @param dispo
     */
    private void reserverDispo(Disponibilite dispo) {
        if (user instanceof Patient) {
            try {
                boolean success = controller.reserverDispo(user, dispo);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Rendez-vous réservé avec succès !");
                    dispose();
                    new AccueilPatientView(user);
                } else {
                    JOptionPane.showMessageDialog(this, "Échec de la réservation.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la réservation : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vous devez être connecté en tant que patient pour réserver un rendez-vous.", "Accès refusé", JOptionPane.WARNING_MESSAGE);
        }
    }

}
