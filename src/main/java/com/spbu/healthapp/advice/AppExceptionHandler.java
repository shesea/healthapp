package com.spbu.healthapp.advice;

import com.spbu.healthapp.exception.UserExistsException;
import com.spbu.healthapp.exception.UserNotExistsException;
import com.spbu.healthapp.exception.UserNotFoundException;
import com.spbu.healthapp.exception.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundException(UserNotFoundException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("code", "9");
        errorMap.put("errorMessage", e.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserExistsException.class)
    public Map<String, String> handleUserExistsException(UserExistsException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("code", "10");
        errorMap.put("errorMessage", e.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserNotExistsException.class)
    public Map<String, String> handleUserExistsException(UserNotExistsException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("code", "11");
        errorMap.put("errorMessage", e.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(WrongPasswordException.class)
    public Map<String, String> handleUserExistsException(WrongPasswordException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("code", "12");
        errorMap.put("errorMessage", e.getMessage());
        return errorMap;
    }
}
