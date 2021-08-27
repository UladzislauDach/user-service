package by.dach.app.service.messaging.registration;

import by.dach.app.model.dto.UserCreateDto;
import by.dach.app.service.messaging.client.Gmail;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class GMailSender implements RegistrationMessagingApi {

    private final Gmail gmail;

    public GMailSender(Gmail gmail) {
        this.gmail = gmail;
    }

    public void sendRegistrationMessage(UserCreateDto userCreateDto) {
        String message = welcomeMessagePart1 + userCreateDto.getFirstName() + welcomeMessagePart2;
        gmail.sendMessage("Welcome to user-service!", message, userCreateDto.getEmail());
    }
}
