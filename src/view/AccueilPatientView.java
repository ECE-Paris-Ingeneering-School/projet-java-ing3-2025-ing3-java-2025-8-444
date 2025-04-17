package view;

import dao.RendezVousDAO;
import model.RendezVous;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AccueilPatientView extends JFrame {

    private Utilisateur user;

    public AccueilPatientView(Utilisateur user) {
        this.user = user;
        setTitle("Espace Patient - Bienvenue " + user.getPrenom());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(231, 141, 82));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("Doc'n'Roll");
        logo.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.add(logo, BorderLayout.WEST);

        JLabel welcome = new JLabel("Bonjour " + user.getPrenom() + " !");
        welcome.setFont(new Font("SansSerif", Font.BOLD, 18));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(welcome, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Déconnexion");
        logoutButton.addActionListener(e -> {
            dispose();
            new ConnexionView();
        });
        header.add(logoutButton, BorderLayout.EAST);

        return header;
    }

    private JPanel createMainPanel() {
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(Color.WHITE);

        JLabel title = new JLabel("Vos rendez-vous :");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultListModel<String> model = new DefaultListModel<>();
        RendezVousDAO rdvDAO = new RendezVousDAO();
        List<RendezVous> liste = rdvDAO.getAllForPatient(user.getId());

        if (liste.isEmpty()) {
            model.addElement("Aucun rendez-vous trouvé.");
        } else {
            for (RendezVous r : liste) {
                model.addElement(r.getDisponibilite().getDate() + " à " + r.getDisponibilite().getHeureDebut() +
                        " - " + r.getSpecialiste().getNom() + " (" + r.getStatut() + ")");
            }
        }

        JList<String> rdvList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(rdvList);

        JButton searchNewRdv = new JButton("Prendre un nouveau rendez-vous");
        searchNewRdv.addActionListener(e -> {
            dispose();
            new RechercheEtPriseRDVView(user);
        });

        main.add(title, BorderLayout.NORTH);
        main.add(scrollPane, BorderLayout.CENTER);
        main.add(searchNewRdv, BorderLayout.SOUTH);

        return main;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        footer.add(new JLabel("Doc'n'Roll © 2025"));
        return footer;
    }
}