package com.hp.backend.exception.custom;

import com.hp.backend.model.CustomError;

public class CustomBadRequestException extends BaseCustomException {

    public CustomBadRequestException(CustomError error) {
        super(error);
        
    }

}
