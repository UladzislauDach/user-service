package by.dach.app.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleNotFoundException extends Exception {
    private long roleId;

    public RoleNotFoundException(String message, long roleId) {
        super(message);
        this.roleId = roleId;
    }
}
