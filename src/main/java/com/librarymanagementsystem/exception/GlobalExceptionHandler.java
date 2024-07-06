package com.librarymanagementsystem.exception;

import com.librarymanagementsystem.dto.ErrorDetailsDto;
import com.librarymanagementsystem.dto.ErrorDto;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the library system application.
 * This class provides centralized exception handling across all controllers in the application.
 * It uses {@link RestControllerAdvice} to catch and handle exceptions, ensuring that meaningful
 * and consistent error responses are returned to clients.
 * <p>
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions related to method argument validation failures.
     * This method captures {@link MethodArgumentNotValidException} exceptions and constructs
     * an {@link ErrorDto} containing details of the validation errors.
     * <p>
     * The response status for this exception is {@code 400 Bad Request}.
     *
     * @param e the exception containing validation errors.
     * @return an {@link ErrorDto} containing a list of validation error details.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorDetailsDto> detailsDtos = new ArrayList<>();

        // Iterate through the field errors and create a detailed error message for each.
        e.getFieldErrors().forEach(fieldError -> {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            ErrorDetailsDto errorDetailsDto = ErrorDetailsDto.builder()
                    .message(message)
                    .field(field)
                    .build();
            detailsDtos.add(errorDetailsDto);
        });

        // Construct and return the ErrorDto with the list of field-specific error messages.
        return ErrorDto.builder()
                .message("Validation Failed")
                .errorMessages(detailsDtos)
                .build();
    }

    /**
     * Handles exceptions when an entity already exists in the system.
     * This method captures {@link AlreadyExistException} exceptions and constructs
     * an {@link ErrorDto} containing the error message.
     * <p>
     * The response status for this exception is {@code 409 Conflict}.
     *
     * @param e the exception indicating that an entity already exists.
     * @return an {@link ErrorDto} containing the conflict error message.
     */
    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handleAlreadyExistException(AlreadyExistException e) {
        // Construct and return the ErrorDto with the conflict message.
        return ErrorDto.builder()
                .message(e.getMessage())
                .build();
    }

    /**
     * Handles exceptions when an entity is not found in the system.
     * This method captures {@link NotFoundException} exceptions and constructs
     * an {@link ErrorDto} containing the error message.
     * <p>
     * The response status for this exception is {@code 404 Not Found}.
     *
     * @param e the exception indicating that an entity was not found.
     * @return an {@link ErrorDto} containing the not found error message.
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundException(NotFoundException e) {
        // Construct and return the ErrorDto with the not found message.
        return ErrorDto.builder()
                .message(e.getMessage())
                .build();
    }

    /**
     * Handles all other exceptions that are not specifically handled by other methods.
     * This method captures general {@link Exception} instances and constructs
     * an {@link ErrorDto} containing the error message.
     * <p>
     * The response status for this exception is {@code 500 Internal Server Error}.
     *
     * @param e the exception containing general error information.
     * @return an {@link ErrorDto} containing the internal server error message.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception e) {
        // Construct and return the ErrorDto with the general error message.
        return ErrorDto.builder()
                .message(e.getMessage())
                .build();
    }
}
