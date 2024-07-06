package com.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing error details in validation or other operations.
 * This class is used to convey specific error information related to particular fields.
 * It helps in providing detailed feedback when validation fails or when other errors occur.
 * <p>
 * Annotations from the Lombok library are used to reduce boilerplate code for
 * creating constructors, getters, setters, etc.
 * <p>
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 */
@AllArgsConstructor
@Data
@Builder
public class ErrorDetailsDto {

    /**
     * The field where the error occurred.
     * This represents the name of the field that caused the validation or processing error.
     */
    private String field;

    /**
     * The error message associated with the field.
     * This message provides a detailed description of the error that occurred.
     */
    private String message;
}
