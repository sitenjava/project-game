package com.game.common.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class APIException {
    private HttpStatus httpStatus;
    private String message;

    public APIException() {
    }
    public static APIException from(HttpStatus httpStatus)
    {
        APIException apiException = new APIException();
        apiException.httpStatus = httpStatus;
        return apiException;
    }

    public APIException withMessage(String message) {
        this.message = message;
        return this;
    }
}
