package exceptions;

/**
 * Exception levée lorsqu'une erreur survient lors de la création d'un DAO (Data Access Object).
 */
public class DaoCreationException extends RuntimeException {

    /**
     * Construit une nouvelle exception DaoCreationException avec un message et une cause spécifique.
     *
     * @param message le message détaillant l'erreur
     * @param cause la cause de l'exception
     */
    public DaoCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
