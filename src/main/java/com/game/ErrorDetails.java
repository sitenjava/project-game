package com.game;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private HttpStatus status;
    private String message;
    private String detail;

    public ErrorDetails(Date date, HttpStatus status, String message, String detail) {
        super();
        this.timestamp = date;
        this.status = status;
        this.message = message;
        this.detail = detail;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}
