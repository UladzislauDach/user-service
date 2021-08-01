package by.dach.app.service;

import by.dach.app.exception.RoleNotFoundException;
import by.dach.app.exception.UserNotFoundException;
import by.dach.app.mapper.UserMapper;
import by.dach.app.model.dto.UserCreateDto;
import by.dach.app.model.dto.UserDto;
import by.dach.app.repository.RoleRepository;
import by.dach.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    public UserCreateDto save(UserCreateDto userCreateDto) {
        userRepository.save(userMapper.userCreateDtoToUser(userCreateDto));
        return userCreateDto;
    }

    public void attachRole(long userId, long roleId) throws UserNotFoundException, RoleNotFoundException {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException("User not found in database", userId);
        if (!roleRepository.existsById(roleId)) throw new RoleNotFoundException("Role not found in database", roleId);
        userRepository.setRoleIdForUser(userId, roleId);
    }
}
