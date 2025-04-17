package view;

import model.Utilisateur;

import javax.swing.*;
import java.awt.*;

public class AccueilAdminView extends JFrame {

    public AccueilAdminView(Utilisateur user) {
        setTitle("Espace Admin - Bienvenue " + user.getNom());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initUI(user);
        setVisible(true);
    }

    private void initUI(Utilisateur user) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Espace Administrateur : gestion globale du système.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        JPanel actions = new JPanel();
        actions.setLayout(new GridLayout(4, 1, 10, 10));

        JButton ajouterSpecialiste = new JButton("Ajouter un spécialiste");
        ajouterSpecialiste.addActionListener(e -> new AjoutSpecialisteView());

        JButton gererRdv = new JButton("Gérer les rendez-vous");
        gererRdv.addActionListener(e -> new GestionRendezVousAdminView());

        JButton gererUtilisateurs = new JButton("Gérer les utilisateurs");
        gererUtilisateurs.addActionListener(e -> new GestionUtilisateursAdminView());

        JButton logout = new JButton("Déconnexion");
        logout.addActionListener(e -> {
            dispose();
            new ConnexionView();
        });

        actions.add(ajouterSpecialiste);
        actions.add(gererRdv);
        actions.add(gererUtilisateurs);
        actions.add(logout);

        panel.add(actions, BorderLayout.CENTER);
        add(panel);
    }
}