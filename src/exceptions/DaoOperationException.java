package exceptions;

public class DaoOperationException extends RuntimeException {
    public DaoOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
