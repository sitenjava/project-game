package com.game.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class APIException extends RuntimeException {
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


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
