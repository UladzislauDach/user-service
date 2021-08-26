package by.dach.app.controller;

import by.dach.app.feign.Ipify;
import by.dach.app.feign.Jplaceholder;
import by.dach.app.model.UserStatus;
import by.dach.app.model.feign.Ip;
import by.dach.app.model.feign.Post;
import by.dach.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class Service {
    private final Jplaceholder jplaceholder;
    private final Ipify ipify;
    private final UserService userService;

    @Autowired
    public Service(Jplaceholder jplaceholder, Ipify ipify,
                   UserService userService) {
        this.jplaceholder = jplaceholder;
        this.ipify = ipify;
        this.userService = userService;
    }


    @RequestMapping
    public String defaultGreeting() {
        return "Hello, World";
    }

    @GetMapping("posts")
    public List<Post> getPosts() {
        return jplaceholder.getPosts();
    }

    @GetMapping("posts/{id}")
    public Post getPostById(@PathVariable long id) {
        return jplaceholder.getPostById(id);
    }

    @GetMapping("ip")
    public Ip getIp() {
        return ipify.getIp();
    }

    @GetMapping("activate-account/{id}")
    public ResponseEntity<String> activateAccount(@PathVariable long id) {
        userService.setUserStatus(id, UserStatus.ACTIVE);
        return ResponseEntity.ok("Success");
    }
}

