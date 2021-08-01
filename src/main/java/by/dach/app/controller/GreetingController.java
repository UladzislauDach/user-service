package by.dach.app.controller;

import by.dach.app.IpifyFeignTest;
import by.dach.app.JplaceholderFeignTest;
import by.dach.app.model.feign.Ip;
import by.dach.app.model.feign.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GreetingController {
    JplaceholderFeignTest jplaceholderFeignTest;
    IpifyFeignTest ipifyFeignTest;

    public GreetingController(JplaceholderFeignTest jplaceholderFeignTest, IpifyFeignTest ipifyFeignTest) {
        this.jplaceholderFeignTest = jplaceholderFeignTest;
        this.ipifyFeignTest = ipifyFeignTest;
    }


    @RequestMapping
    public String defaultGreeting() {
        return "Hello, World";
    }

    @GetMapping("posts")
    List<Post> getPosts() {
        return jplaceholderFeignTest.getPosts();
    }

    @GetMapping("posts/{id}")
    Post getPostById(@PathVariable long id) {
        return jplaceholderFeignTest.getPostById(id);
    }

    @GetMapping("ip")
    Ip getIp() {
        return ipifyFeignTest.getIp();
    }
}

