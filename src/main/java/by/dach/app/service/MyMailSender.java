package by.dach.app.service;

import by.dach.app.model.UserIdEmailFields;
import by.dach.app.model.UserStatus;
import by.dach.app.model.dto.UserCreateDto;
import by.dach.app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
@Primary
public class MyMailSender {
    private final JavaMailSender emailSender;
    private final UserRepository userRepository;

    public MyMailSender(JavaMailSender emailSender, UserRepository userRepository) {
        this.emailSender = emailSender;
        this.userRepository = userRepository;
    }

    void sendRegistrationEmailMessage(UserCreateDto userCreateDto) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            message.setTo(userCreateDto.getEmail());
            message.setSubject("Welcome to user-service");
            message.setText("Hi " + userCreateDto.getFirstName() + "! You registered at " + now.format(format));
            emailSender.send(message);
        } catch (MailException e) {
            log.error("Error mail sending to " + userCreateDto.getEmail(), e);
        }
    }

    @Scheduled(cron = "${scheduled.cron}")
    public void autoCheckPendingUsers() {
        List<UserIdEmailFields> userWithStatusPending = userRepository.getUserByStatus(UserStatus.PENDING);
        if (userWithStatusPending.isEmpty()) {
            return;
        }
        for (UserIdEmailFields users : userWithStatusPending) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(users.getEmail());
                message.setSubject("Activate account");
                message.setText("Go to reference for activate account http://localhost:8080/service/activate-account/"
                        + users.getId());
                emailSender.send(message);
            } catch (MailException e) {
                log.error("Error mail sending to " + users.getEmail(), e);
            }
        }
    }
}
