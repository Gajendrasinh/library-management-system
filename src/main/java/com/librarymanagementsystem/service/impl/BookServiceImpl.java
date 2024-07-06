package com.librarymanagementsystem.service.impl;

import com.librarymanagementsystem.dto.BookDto;
import com.librarymanagementsystem.exception.AlreadyExistException;
import com.librarymanagementsystem.exception.NotFoundException;
import com.librarymanagementsystem.mapper.LibraryMapper;
import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.model.Borrower;
import com.librarymanagementsystem.repository.BookRepository;
import com.librarymanagementsystem.repository.BorrowerRepository;
import com.librarymanagementsystem.service.BookService;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link BookService} interface.
 * This service handles the operations related to books in the library system.
 * It uses {@link BookRepository} for data persistence and {@link LibraryMapper}
 * for mapping between DTOs and entities.
 * <p>
 * This class is annotated with {@link Service}, indicating that it's a service component
 * in the Spring context, and {@link Slf4j}, providing logging capabilities.
 * </p>
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 */
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    // Injecting the BorrowerRepository dependency to handle borrower-related database operations.
    @Autowired
    private BorrowerRepository borrowerRepository;

    // Injecting the LibraryMapper dependency to convert between DTOs and entities.
    @Autowired
    private LibraryMapper mapper;

    // Injecting the BookRepository dependency to handle book-related database operations.
    @Autowired
    private BookRepository bookRepository;

    /**
     * Creates a new book in the library system.
     * <p>
     * This method validates the ISBN to ensure uniqueness, maps the given {@link BookDto}
     * to a {@link Book} entity, saves it using the repository, and then maps the saved entity
     * back to a DTO.
     * </p>
     * @param bookDto the data transfer object containing the book's details
     * @return the created {@link BookDto} with updated information (e.g., ID)
     */
    @Override
    public BookDto createBook(BookDto bookDto) {
        validateIsbn(bookDto);
        Book book = bookRepository.save(mapper.buildBookFromDto(bookDto));
        return mapper.buildBookDtoFromEntity(book);
    }

    /**
     * Retrieves all books in the library system.
     * <p>
     * This method fetches all {@link Book} entities from the repository and maps them
     * to a list of {@link BookDto} objects.
     * </p>
     * @return a list of all books as {@link BookDto}
     */
    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(mapper::buildBookDtoFromEntity).collect(Collectors.toList());
    }

    /**
     * Retrieves a specific book from the library system based on its ID.
     * <p>
     * This method fetches the {@link Book} entity from the repository by its ID
     * and maps it to a {@link BookDto} object.
     * </p>
     * @param bookId the ID of the book to retrieve
     * @return the {@link BookDto} object representing the retrieved book
     * @throws NotFoundException if the book with the given ID does not exist
     */
    @Override
    public BookDto getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + bookId));
        return mapper.buildBookDtoFromEntity(book);
    }

    /**
     * Updates details of an existing book in the library system.
     * <p>
     * This method updates the details of the book identified by the given book ID
     * with the information provided in the {@link BookDto}.
     * </p>
     * @param bookId  the ID of the book to update
     * @param bookDto the {@link BookDto} object containing updated book information
     * @return the updated {@link BookDto} object
     * @throws NotFoundException if the book with the given ID does not exist
     */
    @Override
    public BookDto updateBook(Long bookId, BookDto bookDto) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + bookId));

        existingBook.setTitle(bookDto.getTitle());
        existingBook.setAuthor(bookDto.getAuthor());
        existingBook.setIsbn(bookDto.getIsbn());

        bookRepository.save(existingBook);

        return mapper.buildBookDtoFromEntity(existingBook);
    }

    /**
     * Deletes a book from the library system based on its ID.
     * <p>
     * This method deletes the book identified by the given book ID from the library system.
     * </p>
     * @param bookId the ID of the book to delete
     * @throws NotFoundException if the book with the given ID does not exist
     */
    @Override
    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + bookId));
        bookRepository.delete(book);
    }

    /**
     * Marks a book as borrowed by a borrower.
     * <p>
     * This method finds the {@link Book} and {@link Borrower} entities by their IDs,
     * checks if the book is already borrowed, and then updates the borrowing status.
     * </p>
     * @param bookId the ID of the book to be borrowed
     * @param borrowerId the ID of the borrower
     * @return the updated {@link BookDto} with borrowing details
     * @throws NotFoundException if the book or borrower does not exist
     * @throws AlreadyExistException if the book is already borrowed by the borrower
     */
    @Override
    public BookDto borrowedBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book does not exist"));
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new NotFoundException("Borrower does not exist"));

        if (borrower.getBooks().contains(book)) {
            log.warn("Book is already borrowed by the given borrower, bookId:{}, borrowerId:{}", bookId, borrowerId);
            throw new AlreadyExistException("Book is already borrowed by the borrower");
        }

        book.setBorrowedBy(borrower);
        borrower.getBooks().add(book);
        borrowerRepository.save(borrower);
        book = bookRepository.save(book);
        return mapper.buildBookDtoFromEntity(book);
    }

    /**
     * Marks a book as returned by a borrower.
     * <p>
     * This method finds the {@link Book} and {@link Borrower} entities by their IDs,
     * checks if the book is borrowed by the borrower, and then updates the borrowing status.
     * </p>
     * @param bookId the ID of the book to be returned
     * @param borrowerId the ID of the borrower
     * @return the updated {@link BookDto} with return details
     * @throws NotFoundException if the book or borrower does not exist
     * @throws AlreadyExistException if the book is not borrowed by the borrower
     */
    @Override
    public BookDto returnBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book does not exist"));
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new NotFoundException("Borrower does not borrow this book"));

        if (!borrower.getBooks().contains(book)) {
            log.warn("Book is not borrowed by the given borrower, bookId:{}, borrowerId:{}", bookId, borrowerId);
            throw new AlreadyExistException("Book is not borrowed by the given borrower");
        }

        book.setBorrowedBy(null);
        borrower.getBooks().remove(book);
        borrowerRepository.save(borrower);
        book = bookRepository.save(book);
        return mapper.buildBookDtoFromEntity(book);
    }

    /**
     * Validates the ISBN of the given book.
     * <p>
     * This method checks if a book with the same ISBN already exists in the system
     * with a different title or author. If it does, an {@link AlreadyExistException} is thrown.
     * </p>
     * @param bookDto the data transfer object containing the book's details
     * @throws AlreadyExistException if a book with the same ISBN already exists with a different title or author
     */
    private void validateIsbn(BookDto bookDto) {
        List<Book> books = bookRepository.findByIsbn(bookDto.getIsbn());
        books.forEach(book -> {
            if (!book.getAuthor().equals(bookDto.getAuthor()) || !book.getTitle().equals(bookDto.getTitle())) {
                log.warn("Book already exists with the same ISBN:{}, author:{}, and title:{}", bookDto.getIsbn(), bookDto.getAuthor(), bookDto.getTitle());
                throw new AlreadyExistException("Book already exists with the same title and author");
            }
        });
    }
}