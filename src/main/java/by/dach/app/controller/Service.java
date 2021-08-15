package by.dach.app.controller;

import by.dach.app.IpifyFeignTest;
import by.dach.app.JplaceholderFeignTest;
import by.dach.app.model.UserStatus;
import by.dach.app.model.feign.Ip;
import by.dach.app.model.feign.Post;
import by.dach.app.repository.UserRepository;
import by.dach.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class Service {
    private final JplaceholderFeignTest jplaceholderFeignTest;
    private final IpifyFeignTest ipifyFeignTest;
    private final UserService userService;

    @Autowired
    public Service(JplaceholderFeignTest jplaceholderFeignTest, IpifyFeignTest ipifyFeignTest,
                   UserService userService) {
        this.jplaceholderFeignTest = jplaceholderFeignTest;
        this.ipifyFeignTest = ipifyFeignTest;
        this.userService = userService;
    }


    @RequestMapping
    public String defaultGreeting() {
        return "Hello, World";
    }

    @GetMapping("posts")
    public List<Post> getPosts() {
        return jplaceholderFeignTest.getPosts();
    }

    @GetMapping("posts/{id}")
    public Post getPostById(@PathVariable long id) {
        return jplaceholderFeignTest.getPostById(id);
    }

    @GetMapping("ip")
    public Ip getIp() {
        return ipifyFeignTest.getIp();
    }

    @GetMapping("activate-account/{id}")
    public ResponseEntity<String> activateAccount(@PathVariable long id) {
        userService.setUserStatus(id, UserStatus.ACTIVE);
        return ResponseEntity.ok("Success");
    }
}

