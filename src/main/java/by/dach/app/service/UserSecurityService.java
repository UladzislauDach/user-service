package by.dach.app.service;

import by.dach.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserSecurityService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public String getEncodedPasswordByLogin(String login) {
        return userRepository.getEncodedPasswordByLogin(login);
    }

    public boolean existUserByLoginAndPassword(String login, String password) {
        String encodedPasswordFromDB = getEncodedPasswordByLogin(login);
        return passwordEncoder.matches(password, encodedPasswordFromDB);
    }

    public boolean existByLogin(String login) { //todo может его в UserService
        return userRepository.existsUserByLogin(login);
    }
}
