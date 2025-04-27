package exceptions;

/**
 * Exception levée lorsqu'une disponibilité existante empêche une opération.
 */
public class DisponibiliteExistanteException extends RuntimeException {

    /**
     * Construit une nouvelle exception DisponibiliteExistanteException avec un message spécifique.
     *
     * @param message le message décrivant l'erreur liée à la disponibilité existante
     */
    public DisponibiliteExistanteException(String message) {
        super(message);
    }
}
