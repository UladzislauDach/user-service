package by.dach.app.service.messaging;

import by.dach.app.exception.SendMessageException;
import by.dach.app.model.UserIdEmailFields;
import by.dach.app.model.UserStatus;
import by.dach.app.repository.UserRepository;
import by.dach.app.service.messaging.client.MessagingApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PendingUsersNotificationSender {

    private final UserRepository userRepository;
    private final MessagingApi messagingApi;

    public PendingUsersNotificationSender(UserRepository userRepository, MessagingApi messagingApi) {
        this.userRepository = userRepository;
        this.messagingApi = messagingApi;
    }

    @Scheduled(cron = "${scheduled.cron}")
    public void senMessagePendingUsers() {
        List<UserIdEmailFields> userWithStatusPending = userRepository.getUserByStatus(UserStatus.PENDING);
        if (userWithStatusPending.isEmpty()) {
            return;
        }
        for (UserIdEmailFields user : userWithStatusPending) {
            String message = "Go to reference for activate account http://localhost:8080/service/activate-account/"
                    + user.getId();
            try {
                messagingApi.sendMessage("Activate account", message, user.getEmail());
            } catch (SendMessageException e) {
                log.error("Error send message to pending users", e);
            }

        }
    }
}