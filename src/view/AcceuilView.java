package view;

import javax.swing.*;
import java.awt.*;

public class AcceuilView extends JFrame {

    public AcceuilView() {
        setTitle("Doc'n'Roll - Acceuil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        //on ajoute le tout en visible pour que ça s'affiche
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    //header (toute la bande haute de la page)
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(231, 141, 82));//couleur orange pour le fond
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        //on le "logo" (nom) de l'appli en haut à gauche
        JLabel logo = new JLabel("Doc'n'Roll");//nom à changer
        logo.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.add(logo, BorderLayout.WEST);

        //filtres catégories en haut au centre
        JPanel filters = new JPanel();
        filters.setOpaque(false);
        filters.add(new JComboBox<>(new String[]{"Spécialisation"}));//menu déroulant pour choisir la spécialité qui nous intéresse
        filters.add(new JComboBox<>(new String[]{"critère choix"}));
        header.add(filters, BorderLayout.CENTER);

        //profil
        JPanel profil = new JPanel();
        profil.setOpaque(false);
        profil.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //image profil
        JLabel avatar = new JLabel("F");
        avatar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        avatar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose(); // ferme la page d’accueil
                new ConnexionView(); // ouvre la page de connexion
            }
        });

        avatar.setOpaque(true);
        avatar.setBackground(Color.GREEN);
        avatar.setForeground(Color.WHITE);
        avatar.setPreferredSize(new Dimension(40,40));
        avatar.setHorizontalAlignment(SwingConstants.CENTER);
        avatar.setFont(new Font("SansSerif", Font.BOLD, 16));
        avatar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //Nom profil
        JLabel username = new JLabel("Utilisateur Profil");
        //on ajoute le tout au profil puis le profil à droite du header
        profil.add(avatar);
        profil.add(username);
        header.add(profil, BorderLayout.EAST);

        return header;
    }

    //creation de la partie principale de la page (barre de recheche slogan tout ça)
    private JPanel createMainPanel(){
        JPanel main = new JPanel();
        main.setBackground(new Color(231, 141, 82));//meme orange qu'au dessus
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        //slogan
        JLabel slogan = new JLabel("Slogan blablabla santé");//faut trouver un slogan pitié
        slogan.setFont(new Font("SansSerif", Font.BOLD, 32));
        slogan.setAlignmentX(Component.CENTER_ALIGNMENT);

        //barre de recherche
        JPanel recherche = new JPanel();
        recherche.setBackground(new Color(231, 141, 82));
        JTextField rechercheTexte = new JTextField("Nom, spécialité, lieu...");//texte de la barre de recherche
        JButton rechercheBouton = new  JButton("🔍");//émoji pour faire une image de bouton de recherche
        //on ajoute le tout à la barre de recherche
        recherche.add(rechercheTexte);
        recherche.add(rechercheBouton);

        //maintenant on ajoute le slogan et la barre de recherche à la partie principale de la page
        main.add(Box.createVerticalStrut(80));//on ajoute une "boite" (endroit sur la page) où mettre notre slogan
        main.add(slogan);
        main.add(Box.createVerticalStrut(20));
        main.add(recherche);

        return main;
    }
    //creation du footer
    private JPanel createFooterPanel() {
        JPanel footer = new JPanel(new GridLayout(1, 4));//on fait une grille de 4 colonne une avec le nom de l'appli et 3 avec différentes infos (voir design)
        footer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));//tailler du footer

        footer.setBackground(Color.WHITE);

        footer.add(new JLabel("Doc'n'Roll"));
        //flemme de remplir toutes les infos pour l'instant donc je met ça pour les remplir rapidement
        for (int i = 0; i < 3; i++) {
            JPanel column = new JPanel();
            column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
            column.add(new JLabel("Topic"));
            for (int j = 0; j < 3; j++) {
                column.add(new JLabel("Page"));
            }
            footer.add(column);
        }

        return footer;
    }

}
