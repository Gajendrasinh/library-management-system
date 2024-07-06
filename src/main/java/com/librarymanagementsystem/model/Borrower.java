package com.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing a borrower in the library management system.
 * <p>
 * The {@code Borrower} class is an entity that maps to the {@code borrower} table in the database.
 * It uses JPA annotations to define the mapping and Lombok annotations to generate boilerplate
 * code such as constructors, getters, setters, and builders.
 * </p>
 *
 * <p>
 * Typical usage example:
 * <pre>
 *     Borrower borrower = Borrower.builder()
 *         .name("John Doe")
 *         .email("john.doe@example.com")
 *         .build();
 * </pre>
 * </p>
 *
 * <p>
 * Note: The {@code Borrower} entity has a one-to-many relationship with the {@code Book} entity.
 * </p>
 *
 * @see com.librarymanagementsystem.model.Book
 * @see jakarta.persistence.Entity
 * @see lombok
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SuperBuilder
@Table(name = "borrower")
public class Borrower extends BaseEntity{

    /**
     * Name of the borrower.
     * <p>
     * This field is required and must be unique. It is mapped to the {@code name} column in the {@code borrower} table.
     * </p>
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * Email address of the borrower.
     * <p>
     * This field is required and must be unique. It is mapped to the {@code email} column in the {@code borrower} table.
     * </p>
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Books borrowed by the borrower.
     * <p>
     * This field represents a one-to-many relationship with the {@link Book} entity.
     * It is mapped by the {@code borrowedBy} field in the {@link Book} entity.
     * </p>
     *
     * <p>
     * Note: The {@code HashSet} ensures that the set of books is unique and does not allow duplicates.
     * </p>
     */
    @OneToMany(mappedBy = "borrowedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();

}
