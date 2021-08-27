package by.dach.app.service.messaging.client;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Gmail {
    private final JavaMailSender javaMailSender;

    public Gmail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String subject, String text, String recipient) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(text);
        message.setTo(recipient);
        javaMailSender.send(message);
    }
}
