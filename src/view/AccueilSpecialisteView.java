package view;

import dao.DisponibiliteDAO;
import model.Disponibilite;
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

        JLabel welcome = new JLabel("Bienvenue Dr " + user.getNom() + " !");
        welcome.setFont(new Font("SansSerif", Font.BOLD, 18));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(welcome, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton accueilButton = new JButton("Accueil");
        accueilButton.addActionListener(e -> {
            dispose();
            new AccueilSpecialisteView(user);
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
        main.setBackground(Color.WHITE);

        JLabel title = new JLabel("Vos disponibilités :");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultListModel<String> model = new DefaultListModel<>();
        DisponibiliteDAO dispoDAO = new DisponibiliteDAO();
        List<Disponibilite> liste = dispoDAO.getAllForSpecialiste(user.getId());

        if (liste.isEmpty()) {
            model.addElement("Aucune disponibilité déclarée.");
        } else {
            for (Disponibilite d : liste) {
                model.addElement(d.getDate() + " : " + d.getHeureDebut() + " - " + d.getHeureFin() +
                        " à " + d.getLieu().getNom() +
                        (d.isEstDisponible() ? " (Libre)" : " (Occupé)"));
            }
        }

        JList<String> dispoList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(dispoList);

        JButton addAvailability = new JButton("Ajouter une disponibilité");
        addAvailability.addActionListener(e -> new AjoutDisponibiliteView((Specialiste) user));

        main.add(title, BorderLayout.NORTH);
        main.add(scrollPane, BorderLayout.CENTER);
        main.add(addAvailability, BorderLayout.SOUTH);

        return main;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        footer.add(new JLabel("Doc'n'Roll © 2025"));
        return footer;
    }
}
