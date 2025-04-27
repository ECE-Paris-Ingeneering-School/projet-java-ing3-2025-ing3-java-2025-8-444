package exceptions;

/**
 * Exception levée lorsqu'une erreur survient lors de l'enregistrement d'une disponibilité.
 */
public class DisponibiliteSaveException extends RuntimeException {

    /**
     * Construit une nouvelle exception DisponibiliteSaveException avec un message spécifique.
     *
     * @param message le message décrivant l'erreur lors de l'enregistrement de la disponibilité
     */
    public DisponibiliteSaveException(String message) {
        super(message);
    }
}
