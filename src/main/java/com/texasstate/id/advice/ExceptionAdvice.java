package com.texasstate.id.advice;


import com.texasstate.id.advice.coustomExceptions.ApplicantNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestControllerAdvice
public class ExceptionAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApplicantNotCreatedException.class)
    public Map<String, String> applicantNotCreated(ApplicantNotCreatedException applicantNotCreatedException) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", applicantNotCreatedException.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> unHandled(Exception ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("System Exception", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ExecutionException.class, InterruptedException.class})
    public Map<String, String> threadException(Exception ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Thread Exception", ex.getMessage());
        return errorMap;
    }
}
