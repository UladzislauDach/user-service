package by.dach.app.service.messaging.registration;

import by.dach.app.model.dto.UserCreateDto;
import by.dach.app.service.messaging.client.Telegram;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class TelegramRegistrationMessageSender implements RegistrationMessagingApi {
    private final Telegram telegram;

    public TelegramRegistrationMessageSender(Telegram telegram) {
        this.telegram = telegram;
    }

    @Override
    public void sendRegistrationMessage(UserCreateDto userCreateDto) {
        String message = welcomeMessagePart1 + userCreateDto.getFirstName() + welcomeMessagePart2; //может StringBuilder?
        telegram.sendMessage(message);
    }
}
