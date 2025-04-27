package exceptions;

public class EnvoiMailException extends RuntimeException {
    public EnvoiMailException(String message, Throwable cause) {
        super(message, cause);
    }
}
