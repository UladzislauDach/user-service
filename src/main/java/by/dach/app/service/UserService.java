package by.dach.app.service;

import by.dach.app.exception.RoleNotFoundException;
import by.dach.app.exception.UserNotFoundException;
import by.dach.app.mapper.UserMapper;
import by.dach.app.model.User;
import by.dach.app.model.UserStatus;
import by.dach.app.model.dto.UserCreateDto;
import by.dach.app.model.dto.UserDto;
import by.dach.app.repository.RoleRepository;
import by.dach.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final MyMailSender myMailSender;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    @Transactional
    public UserCreateDto save(UserCreateDto userCreateDto) {
        User savedUser = userMapper.userCreateDtoToUser(userCreateDto);
        savedUser.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        userRepository.save(savedUser);
        myMailSender.sendRegistrationEmailMessage(userCreateDto);
        return userCreateDto;
    }

    @Transactional
    public void attachRole(long userId, long roleId) throws UserNotFoundException, RoleNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found in database", userId);
        }
        if (!roleRepository.existsById(roleId)) {
            throw new RoleNotFoundException("Role not found in database", roleId);
        }
        userRepository.setRoleIdForUser(userId, roleId);
    }

    @Transactional
    public void setUserStatus(long id, UserStatus userStatus) {
        userRepository.setUserStatus(id, userStatus.toString());
    }
}
