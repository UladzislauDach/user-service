package by.dach.app;

import by.dach.app.model.Permission;
import by.dach.app.model.Role;
import by.dach.app.model.User;
import by.dach.app.model.UserStatus;
import by.dach.app.repository.RoleRepository;
import by.dach.app.repository.UserRepository;
import by.dach.app.service.MyMailSender;
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
public class RunApp implements CommandLineRunner {
    final RoleRepository roleRepository;
    final MyMailSender myMailSender;

    public RunApp(RoleRepository roleRepository, MyMailSender myMailSender) {
        this.roleRepository = roleRepository;
        this.myMailSender = myMailSender;
    }

    public static void main(String[] args) {
        SpringApplication.run(RunApp.class, args);
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
