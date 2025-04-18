package view;

import model.Disponibilite;
import model.Specialiste;
import controller.PriseRdvController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;    
import java.util.List;


public class ResultatsRechercheView extends JFrame {

    public ResultatsRechercheView(List<Disponibilite> resultats) {
        setTitle("Résultats de la recherche - Doc'n'Roll");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createResultPanel(resultats), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.setBackground(new Color(52, 152, 219));

        JLabel title = new JLabel("Créneaux disponibles trouvés");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        panel.add(title, BorderLayout.CENTER);

        JButton fermer = new JButton("Fermer");
        fermer.addActionListener(e -> dispose());
        panel.add(fermer, BorderLayout.EAST);

        return panel;
    }

    private JScrollPane createResultPanel(List<Disponibilite> resultats) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        for (Disponibilite d : resultats) {
            Specialiste s = d.getSpecialiste();

            JPanel card = new JPanel();
            card.setLayout(new GridLayout(3, 1));
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10),
                    BorderFactory.createLineBorder(new Color(200, 200, 200))));

            JLabel titre = new JLabel("Dr " + s.getPrenom() + " " + s.getNom() + " - " + s.getSpecialite().getNom());
            titre.setFont(new Font("SansSerif", Font.BOLD, 16));
            JLabel infos = new JLabel("Lieu : " + d.getLieu().getNom() + ", " + d.getLieu().getVille());
            JLabel horaire = new JLabel("Date : " + d.getDate() + " | " + d.getHeureDebut() + " - " + d.getHeureFin());

            card.add(titre);
            card.add(infos);
            card.add(horaire);

            panel.add(card);
            panel.add(Box.createVerticalStrut(10));
        }

        return new JScrollPane(panel);
    }
}
