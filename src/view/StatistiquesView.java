package view;

import dao.DAOFactory;
import dao.SpecialisteDAO;
import dao.UtilisateurDAO;
import dao.RendezVousDAO;
import org.knowm.xchart.*;
import org.knowm.xchart.style.PieStyler;

import model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class StatistiquesView extends JFrame {
    private UtilisateurDAO utilisateurDAO;
    private SpecialisteDAO specialisteDAO;
    private RendezVousDAO rendezVousDAO;
    private Utilisateur utilisateur;

    public StatistiquesView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        setTitle("Doc'n'Roll - Statistiques");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
        this.specialisteDAO = DAOFactory.getSpecialisteDAO();
        this.rendezVousDAO = DAOFactory.getRendezVousDAO();

        add(createHeader(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);

        getContentPane().setBackground(new Color(245, 245, 245));
        setVisible(true);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("Doc'n'Roll - Statistiques");
        logo.setFont(new Font("SansSerif", Font.BOLD, 26));
        logo.setForeground(Color.WHITE);

        JButton retour = new JButton("Retour");
        retour.setBackground(Color.WHITE);
        retour.addActionListener(e -> {
            dispose();
            new AccueilAdminView(utilisateur);
        });

        header.add(retour, BorderLayout.EAST);
        header.add(logo, BorderLayout.WEST);

        return header;
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statsPanel.setPreferredSize(new Dimension(700, 500));

        int nmbUtil = DAOFactory.getUtilisateurDAO().countUtilisateur();
        int nmbSpecial = DAOFactory.getSpecialisteDAO().countSpecialistes();
        int nmbRDV = DAOFactory.getRendezVousDAO().countRendezVous();

        // On affiche quelques statistiques fictives pour l'instant
        JLabel title = new JLabel("Statistiques générales");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel stat1 = new JLabel("Nombre de rendez-vous pris : " + nmbRDV);
        stat1.setFont(new Font("SansSerif", Font.PLAIN, 18));
        stat1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel stat2 = new JLabel("Nombre de spécialistes : " + nmbSpecial);
        stat2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        stat2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel stat3 = new JLabel("Nombre d'Utilisateurs : " + nmbUtil);
        stat3.setFont(new Font("SansSerif", Font.PLAIN, 18));
        stat3.setAlignmentX(Component.CENTER_ALIGNMENT);



        statsPanel.add(title);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        statsPanel.add(stat1);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        statsPanel.add(stat2);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        statsPanel.add(stat3);


        SpecialisteDAO specialisteDAO = new SpecialisteDAO();
        Map<String, Integer> stats = specialisteDAO.countSpecialistesBySpecialite();

        PieChart pieChart = new PieChartBuilder()
                .width(800)
                .height(600)
                .title("Répartition des spécialistes par spécialité")
                .build();


        pieChart.getStyler().setLegendVisible(true);
        pieChart.getStyler().setLegendPosition(PieStyler.LegendPosition.OutsideE);
        pieChart.getStyler().setPlotBackgroundColor(Color.WHITE);
        pieChart.getStyler().setChartBackgroundColor(Color.WHITE);
        pieChart.getStyler().setPlotBorderVisible(false);
        pieChart.getStyler().setAnnotationTextFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        pieChart.getStyler().setAnnotationTextFontColor(Color.BLACK);
        pieChart.getStyler().setPlotContentSize(0.7);


        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            pieChart.addSeries(entry.getKey(), entry.getValue());
        }


        XChartPanel<PieChart> pieChartPanel = new XChartPanel<>(pieChart);
        pieChartPanel.setPreferredSize(new Dimension(500, 400));
        pieChartPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        statsPanel.add(pieChartPanel);


        panel.add(statsPanel);
        return panel;
    }
}

