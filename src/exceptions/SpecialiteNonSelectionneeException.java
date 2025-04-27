package exceptions;

/**
 * Exception levée lorsqu'aucune spécialité n'a été sélectionnée alors qu'elle était requise.
 */
public class SpecialiteNonSelectionneeException extends RuntimeException {

    /**
     * Construit une nouvelle exception SpecialiteNonSelectionneeException avec un message spécifique.
     *
     * @param message le message décrivant l'erreur liée à l'absence de sélection de spécialité
     */
    public SpecialiteNonSelectionneeException(String message) {
        super(message);
    }
}
