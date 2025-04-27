package exceptions;

/**
 * Exception levée lorsqu'une erreur de connexion à la base de données survient.
 */
public class DatabaseConnectionException extends RuntimeException {

    /**
     * Construit une nouvelle exception DatabaseConnectionException avec un message et une cause spécifique.
     *
     * @param message le message décrivant l'erreur de connexion
     * @param cause la cause sous-jacente de l'exception
     */
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
