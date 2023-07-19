package com.hp.backend.exception.custom;

import com.hp.backend.model.CustomError;

public class CustomInternalServerException extends BaseCustomException {

    public CustomInternalServerException(CustomError error) {
        super(error);

    }

}
