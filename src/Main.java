import view.ConnexionView;
import view.AcceuilView;

public class Main {
    public static void main(String[] args) {
        try {
            new AcceuilView();
        } catch (Exception e) {
            e.printStackTrace(); // Affiche une erreur s'il y en a une
        }
    }
}
