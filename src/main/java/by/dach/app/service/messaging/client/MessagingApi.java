package by.dach.app.service.messaging.client;

import by.dach.app.exception.SendMessageException;

public interface MessagingApi {
     void sendMessage(String subject, String text, String recipient) throws SendMessageException;
}
