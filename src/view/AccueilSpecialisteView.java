package view;

import dao.DisponibiliteDAO;
import dao.RendezVousDAO;
import model.Disponibilite;
import model.RendezVous;
import model.Utilisateur;
import model.Specialiste;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AccueilSpecialisteView extends JFrame {

    private Utilisateur user;

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

        JButton accueilButton = new JButton("Accueil");
        accueilButton.setBackground(Color.WHITE);
        accueilButton.addActionListener(e -> {
            dispose();
            new AccueilSpecialisteView(user);
        });

        JButton logoutButton = new JButton("Déconnexion");
        logoutButton.setBackground(Color.WHITE);
        logoutButton.addActionListener(e -> {
            dispose();
            new ConnexionView();
        });

        rightButtons.add(accueilButton);
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

        // Titre
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

        // Bouton ajouter une dispo
        JButton addAvailability = new JButton("➕ Ajouter une disponibilité");
        addAvailability.setBackground(new Color(52, 152, 219));
        addAvailability.setForeground(Color.WHITE);
        addAvailability.setAlignmentX(Component.CENTER_ALIGNMENT);
        addAvailability.setFocusPainted(false);
        addAvailability.setFont(new Font("SansSerif", Font.BOLD, 14));
        addAvailability.addActionListener(e -> new AjoutDisponibiliteView((Specialiste) user));

        // --- Rendez-vous à venir ---
        JLabel rdvLabel = new JLabel("Vos rendez-vous à venir :");
        rdvLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        rdvLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        DefaultListModel<String> modelRDV = new DefaultListModel<>();
        RendezVousDAO rdvDAO = new RendezVousDAO();
        List<RendezVous> rdvs = rdvDAO.getAll(); // on filtre ensuite dans le for

        boolean hasRdv = false;
        for (RendezVous r : rdvs) {
            if (r.getSpecialiste().getId() == user.getId() && !r.getStatut().equalsIgnoreCase("annulé")) {
                modelRDV.addElement(
                        r.getDisponibilite().getDate() + " à " + r.getDisponibilite().getHeureDebut() +
                                " - Patient: " + r.getPatient().getNom() + " (" + r.getStatut() + ")"
                );
                hasRdv = true;
            }
        }

        if (!hasRdv) {
            modelRDV.addElement("Aucun rendez-vous à venir.");
        }

        JList<String> rdvList = new JList<>(modelRDV);
        rdvList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane scrollRDV = new JScrollPane(rdvList);

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

        wrapper.add(content);
        return wrapper;
    }
}
