package by.dach.app.service.messaging;

import by.dach.app.exception.SendMessageException;
import by.dach.app.model.dto.UserCreateDto;
import by.dach.app.service.messaging.client.MessagingApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class RegistrationMessageSender {
    private final MessagingApi messagingApi;

    public RegistrationMessageSender(MessagingApi messagingApi) {
        this.messagingApi = messagingApi;
    }

    public void sendRegistrationMessage(UserCreateDto userCreateDto) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String message = "Hi, " + userCreateDto.getFirstName() + "! You registered at " + now.format(format);
        try {
            messagingApi.sendMessage("Welcome to UserService", message, userCreateDto.getEmail());
        } catch (SendMessageException e) {
            log.error("Error send registration message", e);
        }
    }


}
