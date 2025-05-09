package view;

/* Sources :
https://docs.google.com/document/d/1pqb7wHIJjQyNsmtiUEV_-4jRH8h9gBCJ/edit?tab=t.0
https://boostcamp.omneseducation.com/pluginfile.php/4818934/mod_resource/content/1/Creation_interface_graphique_avec_Swing.pdf
https://www.geeksforgeeks.org/introduction-to-java-swing/
https://stackoverflow.com/questions/tagged/java
https://examples.javacodegeeks.com/java-development/core-java/java-swing-mvc-example/
*/

import model.Utilisateur;

import javax.swing.*;
import java.awt.*;


/**
 * Page d'accueil lorsqu'on se connecte en tant qu'admin
 */
public class AccueilAdminView extends JFrame {

    /**
     * la page d'accueil a en parametre l'utilisateur connecté. composée d'un Header et d'un main panel
     * @param user on envoie l'utilisateur qui utilise l'acceuil
     */
    public AccueilAdminView(Utilisateur user) {
        setTitle("Espace Admin - Bienvenue " + user.getNom());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeader(user), BorderLayout.NORTH);
        add(createMainPanel(user), BorderLayout.CENTER);

        getContentPane().setBackground(new Color(245, 245, 245));
        setVisible(true);
    }

    /**
     * Header, meme bleu qu'avant pour garder la meme identité, le logo, une mention bienvenue avec
     * le nom de la personne connectée, un bouton pour revenir à l'accueil et un bouton pour se déconnecter
     * @param user
     * @return header
     */
    private JPanel createHeader(Utilisateur user) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("Doc'n'Roll");
        logo.setFont(new Font("SansSerif", Font.BOLD, 26));
        logo.setForeground(Color.WHITE);

        JLabel welcome = new JLabel("Bienvenue Admin " + user.getNom());
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
     * Main panel, composé d'un sous panel sur lequel il ya 4 boutons :
     * Ajouter un spécialiste
     * Gerer les rendez vous de la plateforme
     * Gerer les utilisateurs de la plateforme
     * Voir les statistiques de la plateforme
     * @param user
     * @return
     */
    private JPanel createMainPanel(Utilisateur user) {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(245, 245, 245));

        JPanel content = new JPanel(new GridLayout(4, 1, 20, 20));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        content.setPreferredSize(new Dimension(500, 300));

        JButton ajouterSpecialiste = new JButton("Ajouter un spécialiste");
        ajouterSpecialiste.setBackground(new Color(52, 152, 219));
        ajouterSpecialiste.setForeground(Color.WHITE);
        ajouterSpecialiste.addActionListener(e -> {
            dispose();
            new AjoutSpecialisteView();
        });

        JButton gererRdv = new JButton("Gérer les rendez-vous");
        gererRdv.setBackground(new Color(52, 152, 219));
        gererRdv.setForeground(Color.WHITE);
        gererRdv.addActionListener(e -> {
            dispose();
            new GestionRendezVousAdminView();
        });

        JButton gererUtilisateurs = new JButton("Gérer les utilisateurs");
        gererUtilisateurs.setBackground(new Color(52, 152, 219));
        gererUtilisateurs.setForeground(Color.WHITE);
        gererUtilisateurs.addActionListener(e -> {
            dispose();
            new GestionUtilisateursAdminView();
        });

        JButton Statistiques = new JButton("Voir les statistiques");
        Statistiques.setBackground(new Color(52, 152, 219));
        Statistiques.setForeground(Color.WHITE);
        Statistiques.addActionListener(e -> {
            dispose();
            new StatistiquesView(user);
        });

        content.add(ajouterSpecialiste);
        content.add(gererRdv);
        content.add(gererUtilisateurs);
        content.add(Statistiques);

        wrapper.add(content);
        return wrapper;
    }
}