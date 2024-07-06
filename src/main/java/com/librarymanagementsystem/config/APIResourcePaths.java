package com.librarymanagementsystem.config;

/**
 * Defines the API resource paths for the Library Management System.
 * <p>
 * This interface contains constants that represent the URL endpoints
 * used in the application's RESTful API. These constants help maintain
 * a consistent and centralized location for URL paths, making it easier
 * to manage and update them.
 * </p>
 * <p>
 * The paths are organized for different resources like books and borrowers,
 * and include actions like creating, updating, deleting, and retrieving records.
 * </p>
 * <p>
 * Note: The URLs are prefixed with API base paths for versioning and
 * modularization purposes.
 * </p>
 * <p>
 * Example usage: To access the URL for creating a book, use
 * {@code APIResourcePaths.BOOK_URL + APIResourcePaths.CREATE_BOOK_URL}.
 * </p>
 *
 * <pre>
 * Example:
 * {@code
 * @GetMapping(APIResourcePaths.BOOK_URL + APIResourcePaths.GET_ALL_BOOK_URL)
 * public ResponseEntity<List<BookDto>> getAllBooks() {
 *     // Implementation
 * }
 * }
 * </pre>
 *
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 */
public interface APIResourcePaths {

    /**
     * API prefix for all endpoints.
     */
    String API_PREFIX = "api";

    /**
     * API version.
     */
    String API_VERSION = "v1";

    /**
     * Base URL combining API prefix and version.
     */
    String API_BASE_URL = API_PREFIX + "/" + API_VERSION;

    // Book-related endpoints

    /**
     * Base URL for book-related operations.
     */
    String BOOK_URL = API_BASE_URL + "/book";

    /**
     * URL for retrieving all books.
     */
    String GET_ALL_BOOK_URL = "/all";

    /**
     * URL for creating a new book.
     */
    String CREATE_BOOK_URL = "/create";

    /**
     * URL for updating an existing book by ID.
     */
    String UPDATE_BOOK_URL = "/{bookId}";

    /**
     * URL for deleting an existing book by ID.
     */
    String DELETE_BOOK_URL = "/{bookId}";

    /**
     * URL for retrieving a specific book by ID.
     */
    String GET_BOOK_URL = "/{bookId}";

    // Borrower-related endpoints

    /**
     * Base URL for borrower-related operations.
     */
    String BORROWER_URL = API_BASE_URL + "/borrower";

    /**
     * URL for retrieving all borrowers.
     */
    String GET_ALL_BORROWER_URL = "/all";

    /**
     * URL for creating a new borrower.
     */
    String CREATE_BORROWER_URL = "/create";

    /**
     * URL for updating an existing borrower by ID.
     */
    String UPDATE_BORROWER_URL = "/{borrowerId}";

    /**
     * URL for deleting an existing borrower by ID.
     */
    String DELETE_BORROWER_URL = "/{borrowerId}";

    /**
     * URL for retrieving a specific borrower by ID.
     */
    String GET_BORROWER_URL = "/{borrowerId}";
}
