package com.spec.analysis.exceptions;

import com.spec.analysis.exceptions.response_object.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ServerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorMessage> handleExceptions(Exception e, WebRequest req) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UsernameAlreadyTakenException.class)
    public ResponseEntity<ErrorMessage> handleUsernameAlreadyTakenException(UsernameAlreadyTakenException e, WebRequest req) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UsernameAlreadyTakenException e, WebRequest req) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
