package com.librarymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for representing Book data.
 * This class is used to transfer book-related data between different layers
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
public class BookDto {

    /**
     * The unique identifier of the book.
     * This field is managed by the system and typically not provided by the client.
     */
    private Long id;

    /**
     * The author of the book.
     * This field is mandatory and must not be blank.
     * It represents the name of the person or entity that authored the book.
     */
    @NotBlank(message = "Author is a required field")
    private String author;

    /**
     * The title of the book.
     * This field is mandatory and must not be blank.
     * It represents the name of the book.
     */
    @NotBlank(message = "Title is a required field")
    private String title;

    /**
     * The International Standard Book Number (ISBN) of the book.
     * This field is mandatory and must not be blank.
     * ISBN uniquely identifies the book.
     */
    @NotBlank(message = "ISBN is a required field")
    private String isbn;
}