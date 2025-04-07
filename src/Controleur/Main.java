package controleur;

import vue.ConnexionGUI;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConnexionGUI gui = new ConnexionGUI();
            new ConnexionController(gui);
            gui.setVisible(true);
        });
    }
}
