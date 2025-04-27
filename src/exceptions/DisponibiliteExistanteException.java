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
 * Exception levée lorsqu'une disponibilité existante empêche une opération.
 */
public class DisponibiliteExistanteException extends RuntimeException {

    /**
     * Construit une nouvelle exception DisponibiliteExistanteException avec un message spécifique.
     *
     * @param message le message décrivant l'erreur liée à la disponibilité existante
     */
    public DisponibiliteExistanteException(String message) {
        super(message);
    }
}
