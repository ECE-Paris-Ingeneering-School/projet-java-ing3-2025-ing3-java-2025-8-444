package controller;

/* Sources :
https://stackoverflow.com/questions/46734922/how-to-structure-controllers-that-communicate-with-databases
https://openclassrooms.com/fr/courses/7137741-decouplez-votre-architecture-web-pour-des-applications-java-robustes/7184971-extrayez-le-controleur
https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html
*/

import model.Utilisateur;
import exceptions.EnvoiMailException;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import static util.exceptionsConstantes.ERREUR_ENVOI_MAIL;

/**
 * Classe pour gérer l'envoi de mails aux utilisateurs.
 */
public class Mail {

    /**
     * Instance interne de Mail (peut servir pour la réutilisation de la configuration).
     */
    public Mail mail;

    /**
     * Constructeur par défaut.
     * Initialise un nouvel objet Mail sans configuration supplémentaire.
     */
    public Mail() {
        // Pas de configuration supplémentaire nécessaire
    }

    /**
     * Envoie un email à un utilisateur donné avec le contenu spécifié.
     *
     * @param user   Utilisateur destinataire du mail.
     * @param mail   Contenu du mail à envoyer.
     */
    public void envoimail(Utilisateur user, String mail) {
        final String username = "9db28b37dcafa5";
        final String password = "85a2214c2581cc";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("docNroll@mail.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(user.getEmail())
            );
            message.setSubject("Doc Nroll");
            message.setText(mail);

            Transport.send(message);
            System.out.println("Mail envoyé avec succès via Mailtrap !");
        } catch (MessagingException e) {
            throw new EnvoiMailException(ERREUR_ENVOI_MAIL, e);
        } catch (EnvoiMailException e) {
            System.err.println("Erreur lors de l'envoi du mail : " + e.getMessage());
        }
    }
}
