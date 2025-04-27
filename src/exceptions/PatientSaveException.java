package exceptions;

public class PatientSaveException extends RuntimeException {
    public PatientSaveException(String message) {
        super(message);
    }
}
