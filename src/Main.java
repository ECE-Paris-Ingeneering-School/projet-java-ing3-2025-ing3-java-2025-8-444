import view.AcceuilView;

/**
 * Classe principale du programme.
 * Lance l'application en affichant la vue d'accueil.
 */
public class Main {

    /**
     * Constructeur par défaut.
     * Nécessaire pour documenter la création de l'objet Main.
     */
    public Main() {
        // Pas d'initialisation spécifique
    }

    /**
     * Démarre l'application en créant une instance de {@link view.AcceuilView}.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        new AcceuilView();
    }
}
