package com.example.petsproject.pet.web;

import com.example.petsproject.pet.exception.EntityNotFoundException;
import com.example.petsproject.pet.model.ErrorResponse;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.constraints.NotNull;

import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@RestControllerAdvice
public class ExceptionController {

    private static final Logger log = getLogger(ExceptionController.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse notValid(MethodArgumentNotValidException exception) {
        return new ErrorResponse("MethodArgumentNotValidException", 
                buildValidationErrorMessage(exception.getBindingResult()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse notFound(EntityNotFoundException exception) {
        return new ErrorResponse("EntityNotFoundException", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse error(RuntimeException exception) {
        log.error("", exception);
        return new ErrorResponse("RuntimeException", exception.getMessage());
    }

    private String buildValidationErrorMessage(@NotNull BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(e -> String.format("Error on field: '%s'. Wrong value: '%s'. Rejected error: '%s'", e.getField(), e.getRejectedValue(), e.getDefaultMessage()))
                .collect(Collectors.joining("; "));
    }
}
