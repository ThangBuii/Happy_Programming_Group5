package com.hp.backend.exception.custom;

import com.hp.backend.model.CustomError;

public class CustomDuplicateFieldException extends BaseCustomException {

    public CustomDuplicateFieldException(CustomError error) {
        super(error);
        // TODO Auto-generated constructor stub
    }

}
