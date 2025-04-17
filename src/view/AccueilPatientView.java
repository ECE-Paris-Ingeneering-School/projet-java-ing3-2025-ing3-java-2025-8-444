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

        RendezVousDAO rdvDAO = new RendezVousDAO();
        List<RendezVous> liste = rdvDAO.getAllForPatient(user.getId());

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
        JList<String> rdvListPasses = new JList<>(modelPasses);

        JScrollPane scrollFuturs = new JScrollPane(rdvListFuturs);
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
