package com.game.presentation.controller;

import com.game.data.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.handler.ExceptionHandlingWebHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.AuthenticationException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler
{

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleAllException(Exception ex, WebRequest request) {

        return new ErrorMessage(500, ex.getLocalizedMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage IllegalArgumentException() {
        return new ErrorMessage(404, "Không tìm thấy đối tượng");
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage NotFoundException()
    {
        return new ErrorMessage(404,"Không tồn tại");
    }

//    @ExceptionHandler(ForbiddenException.class)
//    @ResponseStatus(value = HttpStatus.FORBIDDEN)
//    public ErrorMessage ForbiddenException()
//    {
//        return new ErrorMessage(403,"Bạn không có quyền truy cập");
//    }
}
