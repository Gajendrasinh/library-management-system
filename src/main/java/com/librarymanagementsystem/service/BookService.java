package com.librarymanagementsystem.service;

import com.librarymanagementsystem.dto.BookDto;

import java.util.List;

/**
 * Service interface for managing books in the library system.
 * <p>
 * This interface defines the contract for book-related operations such as creating,
 * retrieving, updating, deleting, borrowing, and returning books.
 * </p>
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 */
public interface BookService {

    /**
     * Creates a new book in the library system.
     * <p>
     * This method takes a {@link BookDto} containing the details of the book to be created,
     * validates it, and returns the created {@link BookDto} with additional information
     * such as the book's ID.
     * </p>
     *
     * @param bookDto the data transfer object containing the book's details
     * @return the created {@link BookDto} with updated information (e.g., ID)
     */
    BookDto createBook(BookDto bookDto);

    /**
     * Retrieves all books from the library system.
     * <p>
     * This method returns a list of {@link BookDto} objects representing all the books
     * available in the library system.
     * </p>
     *
     * @return a list of all books as {@link BookDto}
     */
    List<BookDto> getAllBooks();

    /**
     * Retrieves a specific book from the library system based on its ID.
     * <p>
     * This method fetches the details of a book identified by the given book ID.
     * </p>
     *
     * @param bookId the ID of the book to retrieve
     * @return the {@link BookDto} object representing the retrieved book
     * @throws com.librarymanagementsystem.exception.NotFoundException if the book with the given ID does not exist
     */
    BookDto getBookById(Long bookId);

    /**
     * Updates details of an existing book in the library system.
     * <p>
     * This method updates the details of the book identified by the given book ID
     * with the information provided in the {@link BookDto}.
     * </p>
     *
     * @param bookId  the ID of the book to update
     * @param bookDto the {@link BookDto} object containing updated book information
     * @return the updated {@link BookDto} object
     * @throws com.librarymanagementsystem.exception.NotFoundException if the book with the given ID does not exist
     */
    BookDto updateBook(Long bookId, BookDto bookDto);

    /**
     * Deletes a book from the library system based on its ID.
     * <p>
     * This method deletes the book identified by the given book ID from the library system.
     * </p>
     *
     * @param bookId the ID of the book to delete
     * @throws com.librarymanagementsystem.exception.NotFoundException if the book with the given ID does not exist
     */
    void deleteBook(Long bookId);

    /**
     * Marks a book as borrowed by a borrower.
     * <p>
     * This method associates a book with a borrower by their respective IDs, updating the
     * borrowing status and returning the updated {@link BookDto} with borrowing details.
     * </p>
     *
     * @param bookId the ID of the book to be borrowed
     * @param borrowerId the ID of the borrower
     * @return the updated {@link BookDto} with borrowing details
     * @throws com.librarymanagementsystem.exception.NotFoundException if the book or borrower does not exist
     * @throws com.librarymanagementsystem.exception.AlreadyExistException if the book is already borrowed by the borrower
     */
    BookDto borrowedBook(Long bookId, Long borrowerId);

    /**
     * Marks a book as returned by a borrower.
     * <p>
     * This method disassociates a book from a borrower by their respective IDs, updating the
     * borrowing status and returning the updated {@link BookDto} with return details.
     * </p>
     *
     * @param bookId the ID of the book to be returned
     * @param borrowerId the ID of the borrower
     * @return the updated {@link BookDto} with return details
     * @throws com.librarymanagementsystem.exception.NotFoundException if the book or borrower does not exist
     * @throws com.librarymanagementsystem.exception.AlreadyExistException if the book is not borrowed by the borrower
     */
    BookDto returnBook(Long bookId, Long borrowerId);
}
