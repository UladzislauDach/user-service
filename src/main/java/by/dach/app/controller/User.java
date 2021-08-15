package by.dach.app.controller;

import by.dach.app.exception.RoleNotFoundException;
import by.dach.app.exception.UserNotFoundException;
import by.dach.app.model.dto.UserCreateDto;
import by.dach.app.model.dto.UserDto;
import by.dach.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class User {
    private final UserService userService;

    User(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    private List<UserDto> all() {
        return userService.findAll();
    }

    @PostMapping("users")
    private UserCreateDto newUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.save(userCreateDto);
    }

    @PostMapping("attach-role")
    @ResponseStatus (HttpStatus.OK)
    private ResponseEntity<Object> attachRole(@RequestParam long userId, @RequestParam long roleId)
            throws UserNotFoundException, RoleNotFoundException {
        userService.attachRole(userId, roleId);
        return ResponseEntity.ok().build();
    }
}
