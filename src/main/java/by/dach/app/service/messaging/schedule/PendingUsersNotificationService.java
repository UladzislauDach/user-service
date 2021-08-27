package by.dach.app.service.messaging.schedule;

import by.dach.app.model.UserIdEmailFields;
import by.dach.app.model.UserStatus;
import by.dach.app.repository.UserRepository;
import by.dach.app.service.messaging.client.Gmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PendingUsersNotificationService {

    private final UserRepository userRepository;
    private final Gmail gmail;

    public PendingUsersNotificationService(UserRepository userRepository, Gmail gmail) {
        this.userRepository = userRepository;
        this.gmail = gmail;
    }

    @Scheduled(cron = "${scheduled.cron}")
    public void autoCheckPendingUsers() {
        List<UserIdEmailFields> userWithStatusPending = userRepository.getUserByStatus(UserStatus.PENDING);
        if (userWithStatusPending.isEmpty()) {
            return;
        }
        for (UserIdEmailFields user : userWithStatusPending) {
            String message = "Go to reference for activate account http://localhost:8080/service/activate-account/"
                    + user.getId();
            gmail.sendMessage("Activate account", message, user.getEmail());

        }
    }
}

