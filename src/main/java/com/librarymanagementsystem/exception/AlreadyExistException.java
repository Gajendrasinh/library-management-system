package com.librarymanagementsystem.exception;

import lombok.Getter;

/**
 * Custom exception class to handle scenarios where an attempt is made to create or register
 * an entity that already exists in the system.
 * This exception should be used to signal a conflict when an entity with the same unique
 * identifier or properties is already present.
 * <p>
 * Inherits from {@link RuntimeException}, allowing it to be thrown during normal operation
 * of the Java Virtual Machine and caught to handle specific cases of conflicts.
 * </p>
 * <p>
 * Annotations from the Lombok library are used to generate boilerplate code for getters.
 * </p>
 * <p>
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 * </p>
 */
@Getter
public class AlreadyExistException extends RuntimeException {

    /**
     * The message that describes the nature of the exception.
     * This field provides detailed information about the reason for the exception.
     */
    private final String message;

    /**
     * Constructs a new AlreadyExistException with the specified detail message.
     * This constructor initializes the exception with the provided message, which can
     * be retrieved using the {@link #getMessage()} method.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public AlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}
