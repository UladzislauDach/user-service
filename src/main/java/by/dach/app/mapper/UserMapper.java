package by.dach.app.mapper;

import by.dach.app.model.Role;
import by.dach.app.model.User;
import by.dach.app.model.UserStatus;
import by.dach.app.model.dto.RoleDto;
import by.dach.app.model.dto.UserCreateDto;
import by.dach.app.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java( java.time.LocalDateTime.now())")
    @Mapping(target = "status", expression = "java( by.dach.app.model.UserStatus.PENDING)")
    User userCreateDtoToUser(UserCreateDto userCreateDto);

    @Mapping(target = "roleDto", source = "role")
    UserDto userToUserDto(User user);

    @Mapping(target = "permissionDto", source = "permission")
    RoleDto roleToRoleDto(Role role);
}
