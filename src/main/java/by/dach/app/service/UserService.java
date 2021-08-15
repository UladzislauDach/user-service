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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final MyMailSender myMailSender;
    private final UserSecurityService userSecurityService;

    public UserService(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository,
                       MyMailSender myMailSender, UserSecurityService userSecurityService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.myMailSender = myMailSender;
        this.userSecurityService = userSecurityService;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    @Transactional
    public UserCreateDto save(UserCreateDto userCreateDto) {
        User savedUser = userMapper.userCreateDtoToUser(userCreateDto);
        savedUser.setPassword(userSecurityService.passwordEncoder().encode(userCreateDto.getPassword())); //todo
        userRepository.save(savedUser);
        myMailSender.registrationEmailMessage(userCreateDto);
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
