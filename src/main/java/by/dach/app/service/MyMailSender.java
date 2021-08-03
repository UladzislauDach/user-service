package by.dach.app.service;

import by.dach.app.model.dto.UserCreateDto;
import by.dach.app.repository.UserRepository;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class MyMailSender {
    private final JavaMailSender emailSender;
    private final UserRepository userRepository;

    public MyMailSender(JavaMailSender emailSender, UserRepository userRepository) {
        this.emailSender = emailSender;
        this.userRepository = userRepository;
    }

    SimpleMailMessage message = new SimpleMailMessage();

    void registrationEmailMessage(UserCreateDto userCreateDto) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        message.setTo(userCreateDto.getEmail());
        message.setSubject("Welcome to user-service");
        message.setText("Hi " + userCreateDto.getFirstName() + "! You registered at " + now.format(format));
        emailSender.send(message);
    }

    @Scheduled(fixedDelay = 1000 * 30)
    public void autoCheckPendingUsers() {
        Map<Long, String> mapEmail = userRepository.getEmailAndIdWhereUserStatusIsPending2();
        if (mapEmail.isEmpty()) return;
        for (Map.Entry<Long, String> entry : mapEmail.entrySet()) {
            try {
                message.setTo(entry.getValue());
                message.setSubject("Activate account");
                message.setText("Go to reference for activate account http://localhost:8080/service/activate-account/"
                        + entry.getKey());
                emailSender.send(message);
            } catch (MailException e) {
                //todo сделать лггер
            }
        }
    }


}
