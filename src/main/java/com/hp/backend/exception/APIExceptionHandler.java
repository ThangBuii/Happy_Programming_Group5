package com.hp.backend.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomDuplicateFieldException;
import com.hp.backend.exception.custom.CustomForbiddenException;
import com.hp.backend.exception.custom.CustomInternalServerException;
import com.hp.backend.exception.custom.CustomNotAuthorizedException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.CustomError;



@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(CustomBadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, CustomError> badRequestException(CustomBadRequestException ex) {
        return ex.getErrors();
    }

    @ExceptionHandler(CustomNotAuthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Map<String, CustomError> notAuthorizedException(CustomNotAuthorizedException ex) {
        return ex.getErrors();
    }

    @ExceptionHandler(CustomDuplicateFieldException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Map<String, CustomError> duplicateFieldException(CustomDuplicateFieldException ex) {
        return ex.getErrors();
    }

    @ExceptionHandler(CustomForbiddenException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Map<String, CustomError> forbiddenException(CustomForbiddenException ex) {
        return ex.getErrors();
    }

    @ExceptionHandler(CustomInternalServerException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Map<String, CustomError> internalServerException(CustomInternalServerException ex) {
        return ex.getErrors();
    }

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Map<String, CustomError> notFoundException(CustomNotFoundException ex) {
        return ex.getErrors();
    }
}
