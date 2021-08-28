package by.dach.app.service.messaging.client;

import by.dach.app.exception.SendMessageException;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Profile("mail")
public class EMailSender implements MessagingApi {
    private final JavaMailSender javaMailSender;

    public EMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String subject, String text, String recipient) throws SendMessageException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(text);
        message.setTo(recipient);
        try {
            javaMailSender.send(message);
        } catch (MailException e){
            throw new SendMessageException("Mail sending error", e);
        }
    }
}
