package com.hp.backend.exception.custom;

import com.hp.backend.model.CustomError;

public class CustomNotAuthorizedException extends BaseCustomException {

    public CustomNotAuthorizedException(CustomError error) {
        super(error);
        // TODO Auto-generated constructor stub
    }

}
