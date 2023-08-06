package com.hp.backend.exception.custom;

import com.hp.backend.model.CustomError;

public class CustomNotFoundException extends BaseCustomException {

    public CustomNotFoundException(CustomError error) {
        super(error);
        
    }

}
