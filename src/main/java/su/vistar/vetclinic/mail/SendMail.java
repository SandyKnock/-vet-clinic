package su.vistar.vetclinic.mail;

import su.vistar.vetclinic.entities.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Created by Владислав on 20.03.2017.
 */
public class SendMail {
    private static final String username = "TestUserTomCat";
    private static final String password = "mtmh8ysc";
    private static final String email  = "TestUserTomCat@yandex.ru";

    public void sendMail(User user){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setSubject("Registration");
            message.setText("Hello"+user.getFirstName()+" "+user.getLastName()
                    + "you password = "+user.getPassword()+" and login = "+user.getSsoId());

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
