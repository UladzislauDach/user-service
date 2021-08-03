package by.dach.app.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private RoleDto roleDto;
}
