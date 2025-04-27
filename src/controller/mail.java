package controller;

import model.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.activation.*;

public class mail {
    public mail mail;

    public void envoimail(Utilisateur user, String mail){
        final String username = "9db28b37dcafa5";
        final String password = "85a2214c2581cc";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
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
            e.printStackTrace();
        }
    }
}