package by.dach.app;

import by.dach.app.model.Permission;
import by.dach.app.model.Role;
import by.dach.app.model.User;
import by.dach.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class RunApp implements CommandLineRunner {
    final UserRepository userRepository;

    public RunApp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RunApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Permission permission1 = new Permission(1, "test_value", LocalDateTime.now());
        Role role1 = new Role(1, "admin", LocalDateTime.now(), List.of(permission1));
        User user1 = new User(1, "Lift123", "123",
                "Oleg", "Lift", LocalDateTime.now(), role1);
        userRepository.save(user1);
    }
}
