package exceptions;

/**
 * Exception levée lorsqu'une erreur survient lors d'une opération sur un DAO (Data Access Object).
 */
public class DaoOperationException extends RuntimeException {

    /**
     * Construit une nouvelle exception DaoOperationException avec un message et une cause spécifique.
     *
     * @param message le message décrivant l'erreur rencontrée
     * @param cause la cause sous-jacente de l'exception
     */
    public DaoOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
