package by.dach.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
