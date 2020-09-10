package com.game.common.Utils;

import com.game.common.MessageConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler
{

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleAllException(Exception ex, WebRequest request) {

        String message = ex.getMessage();
        if (message == null)
            return new ErrorMessage(500, MessageConstants.DataNull);
        return new ErrorMessage(500,ex.getLocalizedMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage IllegalArgumentException() {

        return new ErrorMessage(404, MessageConstants.Not_Found);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage NotFoundException()
    {
        return new ErrorMessage(404,MessageConstants.Not_Found);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorMessage HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex)
    {
        String[] methods = ex.getSupportedMethods();
        String message = MessageConstants.Method_Not_Allowed;
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
        String message = MessageConstants.Unsupported_Media_Type;
        for (MediaType media : medias) {
            message+=" "+ media;
        }
        return new ErrorMessage(415,message);
    }
}