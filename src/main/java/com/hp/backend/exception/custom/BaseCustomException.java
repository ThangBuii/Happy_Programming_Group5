package com.hp.backend.exception.custom;

import java.util.HashMap;
import java.util.Map;

import com.hp.backend.model.CustomError;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseCustomException extends Exception {
    private final Map<String, CustomError> errors;

    public BaseCustomException(CustomError error) {
        this.errors = new HashMap<>();
        this.errors.put("errors", error);
    }
}
