package exceptions;

/**
 * Exception levée lorsqu'une erreur survient lors de l'enregistrement d'un spécialiste.
 */
public class SpecialisteSaveException extends RuntimeException {

    /**
     * Construit une nouvelle exception SpecialisteSaveException avec un message spécifique.
     *
     * @param message le message décrivant l'erreur lors de l'enregistrement du spécialiste
     */
    public SpecialisteSaveException(String message) {
        super(message);
    }
}
