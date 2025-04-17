package view;

import controller.PriseRdvController;
import model.Disponibilite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public class AcceuilView extends JFrame {

    private JTextField rechercheTexte;
    private PriseRdvController controller = new PriseRdvController();
    private final String placeholder = "Rechercher une spécialité, un médecin, un lieu...";

    public AcceuilView() {
        setTitle("Doc'n'Roll - Accueil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("Doc'n'Roll");
        logo.setFont(new Font("SansSerif", Font.BOLD, 26));
        logo.setForeground(Color.WHITE);
        header.add(logo, BorderLayout.WEST);

        JButton connexionBtn = new JButton("Connexion");
        connexionBtn.addActionListener(e -> {
            dispose();
            new ConnexionView();
        });
        header.add(connexionBtn, BorderLayout.EAST);

        return header;
    }

    private JPanel createMainPanel() {
        JPanel main = new JPanel();
        main.setBackground(Color.WHITE);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        JLabel slogan = new JLabel("Trouvez votre spécialiste en quelques clics");
        slogan.setFont(new Font("SansSerif", Font.BOLD, 32));
        slogan.setAlignmentX(Component.CENTER_ALIGNMENT);
        slogan.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        rechercheTexte = new JTextField(30);
        rechercheTexte.setMaximumSize(new Dimension(400, 40));
        rechercheTexte.setFont(new Font("SansSerif", Font.PLAIN, 18));
        rechercheTexte.setText(placeholder);
        rechercheTexte.setForeground(Color.GRAY);

        // comportement du placeholder
        rechercheTexte.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (rechercheTexte.getText().equals(placeholder)) {
                    rechercheTexte.setText("");
                    rechercheTexte.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (rechercheTexte.getText().isEmpty()) {
                    rechercheTexte.setText(placeholder);
                    rechercheTexte.setForeground(Color.GRAY);
                }
            }
        });

        JButton boutonRecherche = new JButton("Rechercher");
        boutonRecherche.setFont(new Font("SansSerif", Font.BOLD, 16));
        boutonRecherche.setAlignmentX(Component.CENTER_ALIGNMENT);
        boutonRecherche.addActionListener(e -> lancerRecherche());

        main.add(Box.createVerticalGlue());
        main.add(slogan);
        main.add(Box.createVerticalStrut(20));
        main.add(rechercheTexte);
        main.add(Box.createVerticalStrut(10));
        main.add(boutonRecherche);
        main.add(Box.createVerticalGlue());

        return main;
    }

    private void lancerRecherche() {
        String texte = rechercheTexte.getText().trim();
        if (texte.isEmpty() || texte.equals(placeholder)) {
            JOptionPane.showMessageDialog(this, "Entrez un critère de recherche.");
            return;
        }
        controller.afficherResultats(texte); // 🔁 ici on lance la vraie vue de résultats
    }



    private JPanel createFooterPanel() {
        JPanel footer = new JPanel(new GridLayout(1, 3));
        footer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        footer.setBackground(new Color(245, 245, 245));

        footer.add(new JLabel("© 2025 Doc'n'Roll"));

        JPanel info1 = new JPanel();
        info1.setLayout(new BoxLayout(info1, BoxLayout.Y_AXIS));
        info1.setOpaque(false);
        info1.add(new JLabel("À propos"));
        info1.add(new JLabel("Notre mission"));
        info1.add(new JLabel("Nos médecins"));

        JPanel info2 = new JPanel();
        info2.setLayout(new BoxLayout(info2, BoxLayout.Y_AXIS));
        info2.setOpaque(false);
        info2.add(new JLabel("Aide"));
        info2.add(new JLabel("Contact"));
        info2.add(new JLabel("Conditions d'utilisation"));

        footer.add(info1);
        footer.add(info2);

        return footer;
    }
}
