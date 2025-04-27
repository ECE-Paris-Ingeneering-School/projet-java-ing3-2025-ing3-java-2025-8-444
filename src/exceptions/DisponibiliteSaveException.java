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
 * Exception levée lorsqu'une erreur survient lors de l'enregistrement d'une disponibilité.
 */
public class DisponibiliteSaveException extends RuntimeException {

    /**
     * Construit une nouvelle exception DisponibiliteSaveException avec un message spécifique.
     *
     * @param message le message décrivant l'erreur lors de l'enregistrement de la disponibilité
     */
    public DisponibiliteSaveException(String message) {
        super(message);
    }
}
