package com.librarymanagementsystem.mapper;

import com.librarymanagementsystem.dto.BookDto;
import com.librarymanagementsystem.dto.BorrowerDto;
import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.model.Borrower;
import org.springframework.stereotype.Component;

/**
 * The LibraryMapper class provides utility methods for converting between data transfer objects (DTOs)
 * and entities in the library system. This class acts as a bridge between the DTOs used for external
 * communication and the entities used for internal data management.
 * <p>
 * It contains methods for mapping {@link BookDto} to {@link Book} and vice versa, as well as for
 * mapping {@link BorrowerDto} to {@link Borrower} and vice versa.
 * <p>
 * This class is marked as a Spring {@link Component} to allow for dependency injection where needed.
 * <p>
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 */
@Component
public class LibraryMapper {

    /**
     * Converts a {@link BookDto} to a {@link Book} entity.
     * This method maps the fields from the BookDto to the corresponding fields in the Book entity.
     * <p>
     * The {@code id} field is not included in the conversion, assuming it is managed by the database.
     *
     * @param bookDto the data transfer object representing a book.
     * @return a {@link Book} entity with values populated from the BookDto.
     */
    public Book buildBookFromDto(BookDto bookDto) {
        return Book.builder()
                .title(bookDto.getTitle())
                .isbn(bookDto.getIsbn())
                .author(bookDto.getAuthor())
                .build();
    }

    /**
     * Converts a {@link Book} entity to a {@link BookDto}.
     * This method maps the fields from the Book entity to the corresponding fields in the BookDto,
     * including the {@code id} field.
     *
     * @param book the entity representing a book.
     * @return a {@link BookDto} with values populated from the Book entity.
     */
    public BookDto buildBookDtoFromEntity(Book book) {
        return BookDto.builder()
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .id(book.getId())
                .build();
    }

    /**
     * Converts a {@link BorrowerDto} to a {@link Borrower} entity.
     * This method maps the fields from the BorrowerDto to the corresponding fields in the Borrower entity.
     * <p>
     * The {@code id} field is not included in the conversion, assuming it is managed by the database.
     *
     * @param borrowerDto the data transfer object representing a borrower.
     * @return a {@link Borrower} entity with values populated from the BorrowerDto.
     */
    public Borrower buildBorrowerFromDto(BorrowerDto borrowerDto) {
        return Borrower.builder()
                .email(borrowerDto.getEmail())
                .name(borrowerDto.getName())
                .build();
    }

    /**
     * Converts a {@link Borrower} entity to a {@link BorrowerDto}.
     * This method maps the fields from the Borrower entity to the corresponding fields in the BorrowerDto,
     * including the {@code id} field.
     *
     * @param borrower the entity representing a borrower.
     * @return a {@link BorrowerDto} with values populated from the Borrower entity.
     */
    public BorrowerDto buildBorrowerDtoFromEntity(Borrower borrower) {
        return BorrowerDto.builder()
                .email(borrower.getEmail())
                .name(borrower.getName())
                .id(borrower.getId())
                .build();
    }
}
