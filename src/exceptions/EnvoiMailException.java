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
