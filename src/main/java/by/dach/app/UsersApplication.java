package by.dach.app;

import by.dach.app.model.Permission;
import by.dach.app.model.Role;
import by.dach.app.repository.RoleRepository;
import by.dach.app.service.messaging.registration.GMailSender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class UsersApplication implements CommandLineRunner {
    final RoleRepository roleRepository;
    final GMailSender GMailSender;

    public UsersApplication(RoleRepository roleRepository, GMailSender GMailSender) {
        this.roleRepository = roleRepository;
        this.GMailSender = GMailSender;
    }

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Permission permission1 = new Permission(1, "test_value", LocalDateTime.now());
        Role role1 = new Role(1, "admin", LocalDateTime.now(), List.of(permission1));
//        User user1 = new User(1, "Lift123", "123",
//                "Oleg", "Lift", "testjavamailsender@mailinator.com", LocalDateTime.now(),
//                UserStatus.PENDING, role1);
        roleRepository.save(role1);
    }
}
