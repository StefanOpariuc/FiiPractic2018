package com.fiipractic.health.errorHandling;

import com.fiipractic.health.exceptions.BadRequestException;
import com.fiipractic.health.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundException(NotFoundException ex) {
        return this.generateErrorDto(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto handleBadRequestException(BadRequestException ex) {
        return this.generateErrorDto(HttpStatus.BAD_REQUEST, ex);
    }

    /**
     * Default handler for exceptions that may occur in the application.
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto defaultHandler(Exception ex) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return errorDto;
    }

    private ErrorDto generateErrorDto(HttpStatus status, Exception ex) {
        ErrorDto errorDto = new ErrorDto(status.value(), ex.getMessage());
        return errorDto;
    }
}
