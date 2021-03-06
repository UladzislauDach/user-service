package by.dach.app.controller;

import by.dach.app.exception.AuthorizationException;
import by.dach.app.exception.RoleNotFoundException;
import by.dach.app.exception.UserNotFoundException;
import by.dach.app.exception.error.AuthorisationError;
import by.dach.app.exception.error.FindEntityErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = {RoleNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    ResponseEntity<FindEntityErrorDto> handlerRoleNotFound(RoleNotFoundException ex) {
        FindEntityErrorDto findEntityErrorDto = new FindEntityErrorDto(ex.getRoleId(), ex.getMessage());
        return new ResponseEntity<>(findEntityErrorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    ResponseEntity<FindEntityErrorDto> handlerUserNotFound(UserNotFoundException ex) {
        FindEntityErrorDto findEntityErrorDto = new FindEntityErrorDto(ex.getUserId(), ex.getMessage());
        return new ResponseEntity<>(findEntityErrorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AuthorizationException.class})
    ResponseEntity<AuthorisationError> handlerUserNotFound(AuthorizationException ex) {
        AuthorisationError authorisationError = new AuthorisationError(ex.getMessage());
        return new ResponseEntity<>(authorisationError, HttpStatus.FORBIDDEN);
    }
}
