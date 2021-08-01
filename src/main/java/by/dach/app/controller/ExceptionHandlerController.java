package by.dach.app.controller;

import by.dach.app.exception.AuthorisationException;
import by.dach.app.exception.error.FindEntityErrorDto;
import by.dach.app.exception.RoleNotFoundException;
import by.dach.app.exception.UserNotFoundException;
import by.dach.app.exception.error.AuthorisationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
    //переделать с наследованием
    @ExceptionHandler (value = {RoleNotFoundException.class})
    ResponseEntity<FindEntityErrorDto> handlerRoleNotFound (RoleNotFoundException ex){
        FindEntityErrorDto findEntityErrorDto = new FindEntityErrorDto(ex.getRoleId(), ex.getMessage());
        return new ResponseEntity<>(findEntityErrorDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler (value = {UserNotFoundException.class})
    ResponseEntity<FindEntityErrorDto> handlerUserNotFound (UserNotFoundException ex){
        FindEntityErrorDto findEntityErrorDto = new FindEntityErrorDto(ex.getUserId(), ex.getMessage());
        return new ResponseEntity<>(findEntityErrorDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler (value = {AuthorisationException.class})
    ResponseEntity<AuthorisationError> handlerUserNotFound (AuthorisationException ex){
       AuthorisationError authorisationError = new AuthorisationError(ex.getMessage());
        return new ResponseEntity<>(authorisationError, HttpStatus.FORBIDDEN);
    }
}
