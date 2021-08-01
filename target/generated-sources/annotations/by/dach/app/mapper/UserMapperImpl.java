package by.dach.app.mapper;

import by.dach.app.model.Permission;
import by.dach.app.model.Role;
import by.dach.app.model.User;
import by.dach.app.model.dto.PermissionDto;
import by.dach.app.model.dto.RoleDto;
import by.dach.app.model.dto.UserCreateDto;
import by.dach.app.model.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-01T12:48:24+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userCreateDtoToUser(UserCreateDto userCreateDto) {
        if ( userCreateDto == null ) {
            return null;
        }

        User user = new User();

        user.setLogin( userCreateDto.getLogin() );
        user.setPassword( userCreateDto.getPassword() );
        user.setFirstName( userCreateDto.getFirstName() );
        user.setLastName( userCreateDto.getLastName() );

        user.setCreatedAt( java.time.LocalDateTime.now() );

        return user;
    }

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setRoleDto( roleToRoleDto( user.getRole() ) );
        userDto.setLogin( user.getLogin() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );

        return userDto;
    }

    @Override
    public RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setPermissionDto( permissionListToPermissionDtoList( role.getPermission() ) );
        roleDto.setName( role.getName() );

        return roleDto;
    }

    protected PermissionDto permissionToPermissionDto(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        PermissionDto permissionDto = new PermissionDto();

        permissionDto.setValue( permission.getValue() );

        return permissionDto;
    }

    protected List<PermissionDto> permissionListToPermissionDtoList(List<Permission> list) {
        if ( list == null ) {
            return null;
        }

        List<PermissionDto> list1 = new ArrayList<PermissionDto>( list.size() );
        for ( Permission permission : list ) {
            list1.add( permissionToPermissionDto( permission ) );
        }

        return list1;
    }
}
