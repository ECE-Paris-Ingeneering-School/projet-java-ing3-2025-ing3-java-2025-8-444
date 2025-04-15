package view;

import model.Specialiste;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;

public class AccueilSpecialisteView extends JFrame {

    public AccueilSpecialisteView(Utilisateur user) {
        setTitle("Espace Spécialiste - Dr " + user.getNom());
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

        JLabel welcome = new JLabel("Bienvenue Dr " + user.getNom() + " !");
        welcome.setFont(new Font("SansSerif", Font.BOLD, 18));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(welcome, BorderLayout.CENTER);

        return header;
    }

    private JPanel createMainPanel() {
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(Color.WHITE);

        JLabel title = new JLabel("Vos disponibilités actuelles :");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Planning fictif (remplir avec DAO : SELECT * FROM disponibilite WHERE specialiste_id = ...)
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("2025-04-14 - 09:00 → 09:30 - Clinique du Centre");
        model.addElement("2025-04-15 - 11:00 → 11:30 - Polyclinique Est");

        JList<String> planningList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(planningList);

        JButton addAvailability = new JButton("Ajouter une disponibilité");
        addAvailability.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Formulaire d'ajout à coder.");
        });

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
