package view;

import model.Patient;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;

public class AccueilPatientView extends JFrame {

    public AccueilPatientView(Utilisateur user) {
        setTitle("Espace Patient - Bienvenue " + user.getPrenom());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeader(user), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createHeader(Utilisateur user) {
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

        return header;
    }

    private JPanel createMainPanel() {
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(Color.WHITE);

        JLabel title = new JLabel("Vos Rendez-vous passés :");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Liste des rendez-vous passés (à remplir via DAO)
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Consultation - Cardiologie - 2025-04-14");
        model.addElement("Consultation - Dermatologie - 2025-03-10");
        JList<String> rdvList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(rdvList);

        JButton searchNewRdv = new JButton("Chercher un nouveau rendez-vous");
        searchNewRdv.addActionListener(e -> {
            // ouvrir interface de recherche
            JOptionPane.showMessageDialog(this, "Interface recherche à coder.");
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
