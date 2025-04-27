package exceptions;

/**
 * Exception levée lorsqu'un utilisateur spécifié est introuvable.
 */
public class UtilisateurNonTrouveException extends RuntimeException {

    /**
     * Construit une nouvelle exception UtilisateurNonTrouveException avec un message spécifique.
     *
     * @param message le message décrivant l'erreur liée à l'utilisateur introuvable
     */
    public UtilisateurNonTrouveException(String message) {
        super(message);
    }
}
