package view;

import model.Utilisateur;
import javax.swing.*;
import java.awt.*;

public class AccueilPatientView extends JFrame {

    public AccueilPatientView(Utilisateur user) {
        setTitle("Espace Patient - Bienvenue " + user.getPrenom());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Bienvenue dans votre espace patient !");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));

        panel.add(label, BorderLayout.CENTER);
        add(panel);
    }
}
