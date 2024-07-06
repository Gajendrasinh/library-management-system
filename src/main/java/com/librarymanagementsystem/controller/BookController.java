package com.librarymanagementsystem.controller;

import com.librarymanagementsystem.config.APIResourcePaths;
import com.librarymanagementsystem.dto.BookDto;
import com.librarymanagementsystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing book-related operations in the library system.
 * This controller provides endpoints for retrieving all books, creating new books,
 * updating book details, deleting books, borrowing a book, and returning a borrowed book.
 * <p>
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 */
@RestController
@RequestMapping(value = APIResourcePaths.BOOK_URL)
public class BookController {

    /**
     * Service responsible for book-related operations.
     */
    @Autowired
    private BookService bookService;

    /**
     * Retrieve a list of all books in the library.
     * This endpoint returns all the books available in the library system.
     *
     * @return a list of BookDto objects representing all books
     */
    @GetMapping(value = APIResourcePaths.GET_ALL_BOOK_URL)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    /**
     * Retrieve details of a specific book by its ID.
     * This endpoint fetches details of a book identified by its unique ID.
     *
     * @param bookId the ID of the book to retrieve
     * @return the BookDto object representing the retrieved book
     */
    @GetMapping(value = APIResourcePaths.GET_BOOK_URL)
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    /**
     * Create a new book entry in the library system.
     * This endpoint allows the addition of a new book to the library with its details.
     *
     * @param bookDto the BookDto object containing book information
     * @return the created BookDto object
     */
    @PostMapping(value = APIResourcePaths.CREATE_BOOK_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@Valid @RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    /**
     * Update details of an existing book in the library system.
     * This endpoint allows updating the details of a book identified by its unique ID.
     *
     * @param bookId  the ID of the book to update
     * @param bookDto the BookDto object containing updated book information
     * @return the updated BookDto object
     */
    @PutMapping(value = APIResourcePaths.UPDATE_BOOK_URL)
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateBook(@PathVariable Long bookId, @Valid @RequestBody BookDto bookDto) {
        return bookService.updateBook(bookId, bookDto);
    }

    /**
     * Delete a book from the library system.
     * This endpoint allows deleting a book identified by its unique ID.
     *
     * @param bookId the ID of the book to delete
     */
    @DeleteMapping(value = APIResourcePaths.DELETE_BOOK_URL)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }

    /**
     * Borrow a book from the library.
     * This endpoint allows a borrower to borrow a specific book identified by bookId.
     * The borrower is identified by borrowerId.
     * The book is marked as borrowed if available.
     *
     * @param bookId     the ID of the book to be borrowed
     * @param borrowerId the ID of the borrower who wants to borrow the book
     * @return the updated BookDto object after borrowing
     */
    @PatchMapping(value = "/borrow/{bookId}/borrower/{borrowerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookDto borrowBook(
            @PathVariable Long bookId,
            @PathVariable Long borrowerId) {
        return bookService.borrowedBook(bookId, borrowerId);
    }

    /**
     * Return a borrowed book to the library.
     * This endpoint allows a borrower to return a specific book identified by bookId.
     * The borrower is identified by borrowerId.
     * The book is marked as returned if it was borrowed.
     *
     * @param bookId     the ID of the book to be returned
     * @param borrowerId the ID of the borrower who is returning the book
     * @return the updated BookDto object after returning
     */
    @PatchMapping(value = "/return/{bookId}/borrower/{borrowerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookDto returnBook(
            @PathVariable Long bookId,
            @PathVariable Long borrowerId) {
        return bookService.returnBook(bookId, borrowerId);
    }
}
