package com.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ApiResponse is a generic class representing a standardized response structure
 * for API responses in the library management system.
 *
 * @param <T> the type of the data field in the response
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    /**
     * Represents the status of the API response, such as "success" or "fail".
     */
    private String status;

    /**
     * Provides a descriptive message about the API response status.
     */
    private String message;

    /**
     * Contains the actual data payload of the API response, which can be of any type.
     */
    private T data;
}
