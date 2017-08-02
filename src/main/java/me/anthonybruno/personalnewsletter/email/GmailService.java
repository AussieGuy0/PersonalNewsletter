package me.anthonybruno.personalnewsletter.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailService implements EmailService {

    private final EmailConfig emailConfig;

    public GmailService(EmailConfig emailConfig) {
       this.emailConfig = emailConfig;
    }

    public void sendEmail(String toAddress, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", emailConfig.getUsername());
        props.put("mail.smtp.password", emailConfig.getPassword());
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(emailConfig.getUsername()));
            InternetAddress internetAddressTo = new InternetAddress(toAddress);
            message.addRecipient(Message.RecipientType.TO, internetAddressTo);

            message.setSubject(subject);
            message.setText(body);

            Transport transport = session.getTransport("smtp");
            transport.connect(host,emailConfig.getUsername() , emailConfig.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            throw new IllegalStateException(me);
        }
    }
}
