package com.andile.gumada.employee.manager.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    //handling specific exception
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException userNotFoundException,
                                                         WebRequest webRequest){
        log.error("User not found for this id error: {}",userNotFoundException.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(),userNotFoundException.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    //handling global exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus
    public ResponseEntity<?> handleGlobalException(Exception exception,
                                                   WebRequest webRequest){
        log.error(":{Application failure occurred!}",exception);
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //handling custom validation errors
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> customValidationErrorHandle(MethodArgumentNotValidException exception){
        log.error(":{}",exception);
        ErrorDetails errorDetails = new ErrorDetails(new Date(),"Validator Error",exception.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}