package com.hp.backend.exception.custom;

import com.hp.backend.model.CustomError;

public class CustomForbiddenException extends BaseCustomException {

    public CustomForbiddenException(CustomError error) {
        super(error);
        // TODO Auto-generated constructor stub
    }

}
