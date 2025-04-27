package view;

/* Sources :
https://docs.google.com/document/d/1pqb7wHIJjQyNsmtiUEV_-4jRH8h9gBCJ/edit?tab=t.0
https://boostcamp.omneseducation.com/pluginfile.php/4818934/mod_resource/content/1/Creation_interface_graphique_avec_Swing.pdf
https://www.geeksforgeeks.org/introduction-to-java-swing/
https://stackoverflow.com/questions/tagged/java
https://examples.javacodegeeks.com/java-development/core-java/java-swing-mvc-example/
*/

import controller.PriseRdvController;
import exceptions.DaoOperationException;
import exceptions.UtilisateurNonTrouveException;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * La classe accueilview sert de page d'accueil lorsqu'on lance l'appli
 */
public class AcceuilView extends JFrame {

    private JTextField rechercheTexte;
    private PriseRdvController controller = new PriseRdvController();
    private final String placeholder = "Rechercher une spécialité, un médecin, un lieu...";
    private Utilisateur utilisateur;
    /**
     * Aucun utilisateur n'est connecté au moment où on lance l'appli
     */
    public AcceuilView() {
        this(null);
    }

    /**Structure de la page d'accueil, titre, taille, composée d'un header, un main panel et un footer
     *
     * @param utilisateur on envoie l'utilisateur qui utilise l'acceuil
     */
    public AcceuilView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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


    /**
     * Header, couleur bleue, le logo à gauche avec le nom de l'appli et le bouton de connexion à droite
     * qui mène à la page de connexion si personne n'est encore connecté, et à la page mon compte sinon
     * @return header
     */
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("Doc'n'Roll");
        logo.setFont(new Font("SansSerif", Font.BOLD, 26));
        logo.setForeground(Color.WHITE);
        header.add(logo, BorderLayout.WEST);

        JButton topButton = new JButton(utilisateur == null ? "Connexion" : "Mon compte");
        topButton.addActionListener(e -> {
            dispose();
            try {
                if (utilisateur == null) {
                    new ConnexionView();
                } else {
                    if (utilisateur instanceof model.Patient) {
                        new AccueilPatientView(utilisateur);
                    } else if (utilisateur instanceof model.Admin) {
                        new AccueilAdminView(utilisateur);
                    } else {
                        new AccueilSpecialisteView(utilisateur);
                    }
                }
                dispose();
            } catch (UtilisateurNonTrouveException ex) {
                JOptionPane.showMessageDialog(this, "Utilisateur non trouvé.");
            } catch (DaoOperationException ex) {
                JOptionPane.showMessageDialog(this, "Erreur d'accès aux informations utilisateur.");
            }

        });
        header.add(topButton, BorderLayout.EAST);

        return header;
    }

    /**
     * Création du panel principal, blanc, slogan, barre de recherche et bouton rechercher qui mène vers
     * // vers la page de recherche
     * @return main
     */
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

    /**
     * Programme pour lancer la recherche, appel au controller qui affiche le resultat de la recherche
     */
    private void lancerRecherche() {
        String texte = rechercheTexte.getText().trim();
        if (texte.isEmpty() || texte.equals(placeholder)) {
            JOptionPane.showMessageDialog(this, "Entrez un critère de recherche.");
            return;
        }
        try {
            controller.afficherResultats(texte, utilisateur);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Une erreur est survenue lors de la recherche : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            System.err.println("Erreur lors de la recherche : " + e.getMessage());
        }
        dispose();
    }


    /**
     * Footer, blanc aussi avec le tag de l'entreprise et les infos pratiques
     * @return footer
     */
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
