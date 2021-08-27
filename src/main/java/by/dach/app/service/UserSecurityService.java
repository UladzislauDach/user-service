package by.dach.app.service;

import by.dach.app.exception.AuthorisationException;
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


    public void checkCredentials(String login, String passwordFromUI) throws AuthorisationException {
        final Optional<UserLoginPasswordFields> user = userRepository.getUserByLogin(login);
        if (user.isEmpty()) {
            throw new AuthorisationException("Логин не существует");
        }
        final String passwordFromDb = user.get().getPassword();
        if (!passwordEncoder.matches(passwordFromUI, passwordFromDb)) {
            throw new AuthorisationException("Не верный пароль");
        }
    }
}
