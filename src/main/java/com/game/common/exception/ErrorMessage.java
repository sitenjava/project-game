package com.game.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorMessage
{
    @JsonProperty("status_code")
    private int statusCode;
    @JsonProperty("message")
    private String message;

    public ErrorMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorMessage() {
    }

    public static ErrorMessage  fromException(APIException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.statusCode = ex.getHttpStatus().value();
        errorMessage.message = ex.getMessage();
        return errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
