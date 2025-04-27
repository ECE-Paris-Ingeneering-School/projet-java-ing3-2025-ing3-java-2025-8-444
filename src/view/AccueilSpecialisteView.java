package view;

import controller.Mail;
import dao.DisponibiliteDAO;
import dao.RendezVousDAO;
import model.Disponibilite;
import model.RendezVous;
import model.Utilisateur;
import model.Specialiste;
import controller.Mail;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AccueilSpecialisteView extends JFrame {

    private Utilisateur user;
    private List<RendezVous> mesRendezVous;

    public AccueilSpecialisteView(Utilisateur user) {
        this.user = user;
        setTitle("Espace Spécialiste - Dr " + user.getNom());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);

        getContentPane().setBackground(new Color(245, 245, 245));
        setVisible(true);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("Doc'n'Roll");
        logo.setFont(new Font("SansSerif", Font.BOLD, 26));
        logo.setForeground(Color.WHITE);

        JLabel welcome = new JLabel("Bienvenue Dr " + user.getNom() + " !");
        welcome.setFont(new Font("SansSerif", Font.PLAIN, 18));
        welcome.setForeground(Color.WHITE);
        welcome.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtons.setOpaque(false);

        JButton logoutButton = new JButton("Déconnexion");
        logoutButton.setBackground(Color.WHITE);
        logoutButton.addActionListener(e -> {
            dispose();
            new ConnexionView();
        });

        rightButtons.add(logoutButton);

        header.add(logo, BorderLayout.WEST);
        header.add(welcome, BorderLayout.CENTER);
        header.add(rightButtons, BorderLayout.EAST);

        return header;
    }

    private JPanel createMainPanel() {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(245, 245, 245));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        content.setPreferredSize(new Dimension(800, 500));

        JLabel title = new JLabel("Votre planning :");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- Disponibilités ---
        JLabel dispoLabel = new JLabel("Vos disponibilités :");
        dispoLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        dispoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        DefaultListModel<String> modelDispos = new DefaultListModel<>();
        DisponibiliteDAO dispoDAO = new DisponibiliteDAO();
        List<Disponibilite> dispos = dispoDAO.getAllForSpecialiste(user.getId());

        if (dispos.isEmpty()) {
            modelDispos.addElement("Aucune disponibilité déclarée.");
        } else {
            for (Disponibilite d : dispos) {
                modelDispos.addElement(
                        d.getDate() + " : " + d.getHeureDebut() + " - " + d.getHeureFin() +
                                " à " + d.getLieu().getNom() +
                                (d.isEstDisponible() ? " (Libre)" : " (Occupé)")
                );
            }
        }

        JList<String> dispoList = new JList<>(modelDispos);
        dispoList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane scrollDispos = new JScrollPane(dispoList);

        JButton addAvailability = new JButton("➕ Ajouter une disponibilité");
        addAvailability.setBackground(new Color(52, 152, 219));
        addAvailability.setForeground(Color.WHITE);
        addAvailability.setAlignmentX(Component.CENTER_ALIGNMENT);
        addAvailability.setFocusPainted(false);
        addAvailability.setFont(new Font("SansSerif", Font.BOLD, 14));
        addAvailability.addActionListener(e -> new AjoutDisponibiliteView((Specialiste) user));

        // --- Rendez-vous ---
        JLabel rdvLabel = new JLabel("Vos rendez-vous :");
        rdvLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        rdvLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        DefaultListModel<String> modelRDV = new DefaultListModel<>();
        RendezVousDAO rdvDAO = new RendezVousDAO();
        List<RendezVous> rdvs = rdvDAO.getAll();

        mesRendezVous = new ArrayList<>();

        boolean hasRdv = false;
        for (RendezVous r : rdvs) {
            if (r.getSpecialiste().getId() == user.getId() && !r.getStatut().equalsIgnoreCase("annulé")) {
                modelRDV.addElement(
                        r.getDisponibilite().getDate() + " à " + r.getDisponibilite().getHeureDebut() +
                                " - Patient: " + r.getPatient().getNom() + " (" + r.getStatut() + ")"
                );
                mesRendezVous.add(r);
                hasRdv = true;
            }
        }

        if (!hasRdv) {
            modelRDV.addElement("Aucun rendez-vous pour l'instant.");
        }

        JList<String> rdvList = new JList<>(modelRDV);
        rdvList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane scrollRDV = new JScrollPane(rdvList);

        // Bouton d'action
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton refuserButton = new JButton("Supprimer");
        refuserButton.setBackground(new Color(231, 76, 60));
        refuserButton.setForeground(Color.WHITE);
        refuserButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        refuserButton.setPreferredSize(new Dimension(160, 30));
        refuserButton.setFocusPainted(false);

        refuserButton.addActionListener(e -> {
            int selectedIndex = rdvList.getSelectedIndex();
            if (selectedIndex != -1 && selectedIndex < mesRendezVous.size()) {
                RendezVous rdv = mesRendezVous.get(selectedIndex);
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Êtes-vous sûr de vouloir supprimer ce rendez-vous ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    // Envoyer les emails avant suppression
                    Mail mailer = new Mail();
                    mailer.envoimail(rdv.getPatient(), "Bonjour " + rdv.getPatient().getPrenom() + ",\nVotre rendez-vous Doc'n'Roll avec Dr " + rdv.getSpecialiste().getNom() + " a été annulé.");
                    mailer.envoimail(rdv.getSpecialiste(), "Bonjour Dr " + rdv.getSpecialiste().getNom() + ",\nVous avez annulé un rendez-vous avec " + rdv.getPatient().getPrenom() + " " + rdv.getPatient().getNom() + ".");

                    new RendezVousDAO().delete(rdv);
                    JOptionPane.showMessageDialog(this, "Rendez-vous supprimé et mail envoyé.");
                    dispose();
                    new AccueilSpecialisteView(user);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un rendez-vous.");
            }
        });

        // Assemblage final
        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(dispoLabel);
        content.add(Box.createVerticalStrut(10));
        content.add(scrollDispos);
        content.add(Box.createVerticalStrut(10));
        content.add(addAvailability);
        content.add(Box.createVerticalStrut(30));
        content.add(rdvLabel);
        content.add(Box.createVerticalStrut(10));
        content.add(scrollRDV);
        content.add(Box.createVerticalStrut(10));
        content.add(buttonPanel);
        buttonPanel.add(refuserButton);

        wrapper.add(content);
        return wrapper;
    }
}
