package exceptions;

/**
 * Exception levée lorsqu'une erreur survient lors de l'envoi d'un e-mail.
 */
public class EnvoiMailException extends RuntimeException {

    /**
     * Construit une nouvelle exception EnvoiMailException avec un message et une cause spécifique.
     *
     * @param message le message décrivant l'erreur survenue lors de l'envoi de l'e-mail
     * @param cause la cause sous-jacente de l'exception
     */
    public EnvoiMailException(String message, Throwable cause) {
        super(message, cause);
    }
}
