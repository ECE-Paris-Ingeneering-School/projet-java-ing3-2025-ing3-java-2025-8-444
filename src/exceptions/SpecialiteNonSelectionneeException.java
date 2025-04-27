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
