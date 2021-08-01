package by.dach.app.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends Exception {
    private long userId;

    public UserNotFoundException(String message, long userId) {
        super(message);
        this.userId = userId;
    }
}
