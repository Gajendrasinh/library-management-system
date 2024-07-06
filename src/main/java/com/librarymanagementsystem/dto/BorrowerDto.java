package com.librarymanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for representing Borrower data.
 * This class is used to transfer borrower-related data between different layers
 * of the application, such as between the controller and the service layer.
 * <p>
 * Annotations from the Lombok library are used to reduce boilerplate code for
 * creating constructors, getters, setters, etc.
 * <p>
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BorrowerDto {

    /**
     * The unique identifier of the borrower.
     * This field is managed by the system and typically not provided by the client.
     */
    private Long id;

    /**
     * The name of the borrower.
     * This field is mandatory and must not be blank.
     * It represents the full name of the borrower.
     */
    @NotBlank(message = "Name is a required field")
    private String name;

    /**
     * The email address of the borrower.
     * This field is mandatory and must not be blank.
     * It must be a valid email format.
     */
    @NotBlank(message = "Email is a required field")
    @Email(message = "Email should be valid")
    private String email;
}
