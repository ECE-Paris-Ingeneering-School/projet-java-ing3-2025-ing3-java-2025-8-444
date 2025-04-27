package exceptions;

/* Sources :
https://drive.google.com/file/d/154Z6ZiSThO2lEeHvWb1aVFBCzcszTLSs/view
https://boostcamp.omneseducation.com/course/view.php?id=377193&section=4#tabs-tree-start
Démo du code Exception
Démo du code DivisionParZero
Démo du code sur la sérialisation
https://drive.google.com/file/d/1t6AgqWMwVD9TGQZHzgVvMnynkZmbsDjF/view
*/

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
