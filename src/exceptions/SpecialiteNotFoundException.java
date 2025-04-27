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
