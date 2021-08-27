package by.dach.app.service.messaging.registration;

import by.dach.app.model.dto.UserCreateDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface RegistrationMessagingApi {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String welcomeMessagePart1 = "Hi, ";
    String welcomeMessagePart2 = "! You registered at " + now.format(format);

    void sendRegistrationMessage(UserCreateDto  userCreateDto);
}
