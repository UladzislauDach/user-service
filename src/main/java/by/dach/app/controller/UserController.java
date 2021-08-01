package by.dach.app.controller;

import by.dach.app.exception.RoleNotFoundException;
import by.dach.app.exception.UserNotFoundException;
import by.dach.app.model.dto.UserCreateDto;
import by.dach.app.model.dto.UserDto;
import by.dach.app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {
    private final UserService userService;


    UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("users")
    List<UserDto> all() {
        return userService.findAll();
    }

    @PostMapping("users")
    UserCreateDto newUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.save(userCreateDto);
    }

    @PostMapping("attach-role")
    String  attachRole(@RequestParam long userId, @RequestParam long roleId) throws UserNotFoundException, RoleNotFoundException {
        userService.attachRole(userId, roleId);
        return "Role attached successfully";
    }
}
