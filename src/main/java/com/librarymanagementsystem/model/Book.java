package com.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Entity class representing a book in the library management system.
 * <p>
 * The {@code Book} class is an entity that maps to the {@code book} table in the database.
 * It uses JPA annotations to define the mapping and Lombok annotations to generate boilerplate
 * code such as constructors, getters, setters, and builders.
 * </p>
 *
 * <p>
 * Typical usage example:
 * <pre>
 *     Book book = Book.builder()
 *         .author("Author Name")
 *         .title("Book Title")
 *         .isbn("123-4567890123")
 *         .build();
 * </pre>
 * </p>
 *
 * <p>
 * Note: The {@code Book} entity has a many-to-one relationship with the {@link Borrower} entity.
 * </p>
 *
 * @see com.librarymanagementsystem.model.Borrower
 * @see jakarta.persistence.Entity
 * @see lombok
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SuperBuilder
@Table(name = "book")
public class Book extends BaseEntity{

    /**
     * Author of the book.
     * <p>
     * This field is required and is mapped to the {@code author} column in the {@code book} table.
     * </p>
     */
    @Column(name = "author", nullable = false)
    private String author;

    /**
     * Title of the book.
     * <p>
     * This field is required and is mapped to the {@code title} column in the {@code book} table.
     * </p>
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * ISBN of the book.
     * <p>
     * This field is required and is mapped to the {@code isbn} column in the {@code book} table.
     * </p>
     */
    @Column(name = "isbn", nullable = false)
    private String isbn;

    /**
     * The borrower who has borrowed the book.
     * <p>
     * This field represents a many-to-one relationship with the {@link Borrower} entity.
     * It is mapped by the {@code borrowedBy} field in the {@link Borrower} entity.
     * </p>
     *
     * <p>
     * Note: A book can be borrowed by only one borrower at a time.
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY) // Ensure lazy fetching for borrower
    @JoinColumn(name = "borrower_id")
    private Borrower borrowedBy;}
