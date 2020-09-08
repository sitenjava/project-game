package com.game.presentation.controller;

import com.game.data.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.List;

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

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorMessage HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex)
    {
        String[] methods = ex.getSupportedMethods();
        String message = "Không hỗ trợ method này , những method được hỗ trợ là :";
        for (String s : methods) {
            message+=" "+s;
        }
        return new ErrorMessage(405,message);
    }
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ErrorMessage HttpRequestMethodNotSupportedException(HttpMediaTypeNotSupportedException ex)
    {
        List<MediaType> medias = ex.getSupportedMediaTypes();
        String message = "Không hỗ trợ media type này , những media type được hỗ trợ là :";
        for (MediaType media : medias) {
            message+=" "+ media;
        }
        return new ErrorMessage(401,message);
    }
}
