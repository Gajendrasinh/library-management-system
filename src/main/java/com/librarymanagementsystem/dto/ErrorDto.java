package com.librarymanagementsystem.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing error responses.
 * This class is used to convey error messages and detailed error information
 * when operations fail due to validation issues or other errors.
 * <p>
 * Annotations from the Lombok library are used to reduce boilerplate code for
 * creating constructors, getters, setters, etc.
 * </p>
 * <p>
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 * </p>
 */
@AllArgsConstructor
@Data
@Builder
public class ErrorDto {

    /**
     * The general error message describing the nature of the error.
     * This message provides a high-level overview of the error.
     */
    private String message;

    /**
     * A list of detailed error messages related to specific fields.
     * Each entry in the list represents an error message associated with a particular field,
     * providing more granular detail about the error.
     */
    private List<ErrorDetailsDto> errorMessages;
}