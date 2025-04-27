package view;

import controller.Mail;
import dao.RendezVousDAO;
import exceptions.DaoOperationException;
import model.RendezVous;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Page d'accueil lorsqu'on se connecte en tant que patient
 */
public class AccueilPatientView extends JFrame {

    private Utilisateur user;

    /**
     * Header et main panel
     * @param user on envoie l'utilisateur qui utilise le tableau de bord
     */
    public AccueilPatientView(Utilisateur user) {
        this.user = user;
        setTitle("Espace Patient - Bienvenue " + user.getPrenom());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);

        getContentPane().setBackground(new Color(245, 245, 245));
        setVisible(true);
    }

    /**
     * Header bleu, logo à gauche, message de bienvenu au milieu et bouton accueil et déconnexion à droite
     * @return header
     */
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("Doc'n'Roll");
        logo.setFont(new Font("SansSerif", Font.BOLD, 26));
        logo.setForeground(Color.WHITE);

        JLabel welcome = new JLabel("Bonjour " + user.getPrenom() + " !");
        welcome.setFont(new Font("SansSerif", Font.PLAIN, 18));
        welcome.setForeground(Color.WHITE);
        welcome.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtons.setOpaque(false);

        JButton accueilButton = new JButton("Accueil");
        accueilButton.setBackground(Color.WHITE);
        accueilButton.addActionListener(e -> {
            dispose();
            new AcceuilView(user);
        });

        JButton logoutButton = new JButton("Déconnexion");
        logoutButton.setBackground(Color.WHITE);
        logoutButton.addActionListener(e -> {
            dispose();
            new AcceuilView();
        });

        rightButtons.add(accueilButton);
        rightButtons.add(logoutButton);

        header.add(logo, BorderLayout.WEST);
        header.add(welcome, BorderLayout.CENTER);
        header.add(rightButtons, BorderLayout.EAST);

        return header;
    }

    /**
     * Main panel, blanc, liste des rendez vous à venir du patient
     * Boutons annuler un rendez vous et Prendre un nouveau rendez vous qui renvoie vers une page
     * de réservation
     * @return main
     */
    private JPanel createMainPanel() {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(245, 245, 245));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        content.setPreferredSize(new Dimension(800, 500));

        JLabel title = new JLabel("Vos rendez-vous à venir :");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        DefaultListModel<String> modelFuturs = new DefaultListModel<>();
        DefaultListModel<String> modelPasses = new DefaultListModel<>();

        List<RendezVous> liste = new ArrayList<>();
        try {
            liste = new RendezVousDAO().getAllForPatient(user.getId());
        } catch (DaoOperationException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des rendez-vous : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        for (RendezVous r : liste) {
            String info = r.getDisponibilite().getDate() + " à " + r.getDisponibilite().getHeureDebut() +
                    " - " + r.getSpecialiste().getNom() + " (" + r.getStatut() + ")";
            if (r.getStatut().equalsIgnoreCase("passé")) {
                info += r.getNotes() != null && !r.getNotes().isEmpty() ? "\n\u2022 Note : " + r.getNotes() : "";
                modelPasses.addElement(info);
            } else {
                modelFuturs.addElement(info);
            }
        }

        JList<String> rdvListFuturs = new JList<>(modelFuturs);
        rdvListFuturs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollFuturs = new JScrollPane(rdvListFuturs);

        JButton annulerButton = new JButton("🗑 Annuler le rendez-vous sélectionné");
        annulerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        annulerButton.setBackground(new Color(231, 76, 60));
        annulerButton.setForeground(Color.WHITE);
        annulerButton.setFocusPainted(false);
        annulerButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        List<RendezVous> finalListe = liste;
        annulerButton.addActionListener(e -> {
            int selectedIndex = rdvListFuturs.getSelectedIndex();
            if (selectedIndex != -1 && selectedIndex < finalListe.size()) {
                RendezVous rdv = finalListe.get(selectedIndex);
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Souhaitez-vous vraiment annuler ce rendez-vous ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        boolean success = new RendezVousDAO().annuler(rdv);
                        if (success) {
                            Mail mail = new Mail();

                            String sujetSpecialiste = "Annulation de rendez-vous";
                            String messageSpecialiste = "Bonjour " + rdv.getSpecialiste().getPrenom() + ",\n\n" +
                                    "Le patient " + user.getPrenom() + " " + user.getNom() +
                                    " a annulé son rendez-vous prévu le " +
                                    rdv.getDisponibilite().getDate() + " à " +
                                    rdv.getDisponibilite().getHeureDebut() + ".\n\n" +
                                    "Merci de votre compréhension.\n\nDoc'n'Roll.";

                            mail.envoimail(rdv.getSpecialiste(), sujetSpecialiste + "\n" + messageSpecialiste);

                            String sujetPatient = "Confirmation d'annulation de rendez-vous";
                            String messagePatient = "Bonjour " + user.getPrenom() + ",\n\n" +
                                    "Votre rendez-vous avec " + rdv.getSpecialiste().getPrenom() + " " + rdv.getSpecialiste().getNom() +
                                    " prévu le " + rdv.getDisponibilite().getDate() + " à " +
                                    rdv.getDisponibilite().getHeureDebut() + " a bien été annulé.\n\n" +
                                    "À bientôt sur Doc'n'Roll !";

                            mail.envoimail(user, sujetPatient + "\n" + messagePatient);

                            JOptionPane.showMessageDialog(this, "Rendez-vous annulé avec succès.");
                            dispose();
                            new AccueilPatientView(user);
                        } else {
                            JOptionPane.showMessageDialog(this, "Erreur lors de l'annulation.");
                        }
                    } catch (DaoOperationException ex) {
                        JOptionPane.showMessageDialog(this, "Erreur lors de l'annulation : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un rendez-vous à venir.");
            }
        });

        JList<String> rdvListPasses = new JList<>(modelPasses);
        JScrollPane scrollPasses = new JScrollPane(rdvListPasses);

        JLabel sousTitrePasses = new JLabel("Rendez-vous passés :");
        sousTitrePasses.setFont(new Font("SansSerif", Font.BOLD, 18));
        sousTitrePasses.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton searchNewRdv = new JButton("Prendre un nouveau rendez-vous");
        searchNewRdv.setBackground(new Color(52, 152, 219));
        searchNewRdv.setForeground(Color.WHITE);
        searchNewRdv.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchNewRdv.addActionListener(e -> {
            dispose();
            new RechercheEtPriseRDVView(user);
        });

        content.add(title);
        content.add(Box.createVerticalStrut(10));
        content.add(scrollFuturs);
        content.add(Box.createVerticalStrut(10));
        content.add(annulerButton);
        content.add(Box.createVerticalStrut(20));
        content.add(sousTitrePasses);
        content.add(Box.createVerticalStrut(10));
        content.add(scrollPasses);
        content.add(Box.createVerticalStrut(20));
        content.add(searchNewRdv);

        wrapper.add(content);
        return wrapper;
    }

}
