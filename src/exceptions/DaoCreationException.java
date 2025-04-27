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
 * Exception levée lorsqu'une erreur survient lors de la création d'un DAO (Data Access Object).
 */
public class DaoCreationException extends RuntimeException {

    /**
     * Construit une nouvelle exception DaoCreationException avec un message et une cause spécifique.
     *
     * @param message le message détaillant l'erreur
     * @param cause la cause de l'exception
     */
    public DaoCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
