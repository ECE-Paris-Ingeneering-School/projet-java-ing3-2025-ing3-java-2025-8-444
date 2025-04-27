package exceptions;

/**
 * Exception levée lorsqu'une spécialité demandée est introuvable.
 */
public class SpecialiteNotFoundException extends RuntimeException {

    /**
     * Construit une nouvelle exception SpecialiteNotFoundException avec un message spécifique.
     *
     * @param message le message décrivant l'erreur liée à la spécialité introuvable
     */
    public SpecialiteNotFoundException(String message) {
        super(message);
    }
}
