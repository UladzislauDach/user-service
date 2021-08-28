package by.dach.app.service;

import by.dach.app.exception.AuthorizationException;
import by.dach.app.model.UserLoginPasswordFields;
import by.dach.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserSecurityService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void checkCredentials(String login, String passwordFromUI) throws AuthorizationException {
        final Optional<UserLoginPasswordFields> user = userRepository.getUserByLogin(login);
        if (user.isEmpty()) {
            throw new AuthorizationException("Логин не существует");
        }
        final String passwordFromDb = user.get().getPassword();
        if (!passwordEncoder.matches(passwordFromUI, passwordFromDb)) {
            throw new AuthorizationException("Не верный пароль");
        }
    }
}
