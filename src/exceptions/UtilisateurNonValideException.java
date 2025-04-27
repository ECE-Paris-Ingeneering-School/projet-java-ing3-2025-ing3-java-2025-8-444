package exceptions;

public class UtilisateurNonValideException extends RuntimeException {
    public UtilisateurNonValideException(String message) {
        super(message);
    }
}
