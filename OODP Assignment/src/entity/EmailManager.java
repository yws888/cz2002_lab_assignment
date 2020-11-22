package entity;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.activation.*;

public class EmailManager {
    public EmailManager(){}

    /**
     * Returns a String that will be the response from javamail of whether the email is successfully sent.
     *
     * @param email
     * @param messageEntry
     * @return
     */
    public String sendEmail(String email, String messageEntry){
        final String username = "cz2002.app@gmail.com";
        final String password = "Cz2002Application";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Course "+messageEntry+" on waitlist allocated");
            message.setText("Course "+messageEntry+" on waitlist has been accepted.");

            Transport.send(message);

            return "Message to "+email+" sent";

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
