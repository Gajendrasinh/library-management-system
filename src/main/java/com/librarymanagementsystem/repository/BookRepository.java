package com.librarymanagementsystem.repository;

import com.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Book} entities.
 * <p>
 * This interface extends the {@link JpaRepository} interface to provide CRUD (Create, Read, Update, Delete)
 * operations for the {@link Book} entity. It is annotated with {@link Repository} to indicate that it is a
 * Spring Data repository.
 * </p>
 *
 * <p>
 * In addition to the standard CRUD methods, this repository provides a custom query method
 * to find books by their ISBN.
 * </p>
 *
 * <p>
 * Typical usage example:
 * <pre>
 *     List&lt;Book&gt; booksByIsbn = bookRepository.findByIsbn("123-456-789");
 *     Optional&lt;Book&gt; book = bookRepository.findById(1L);
 *     bookRepository.save(new Book());
 * </pre>
 * </p>
 *
 * <p>
 * Note: The Spring Data JPA repository abstraction significantly reduces the amount of boilerplate
 * code required to interact with the database.
 * </p>
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 * @see com.librarymanagementsystem.model.Book
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.stereotype.Repository
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Finds and returns a list of books with the specified ISBN.
     * <p>
     * This method signature follows Spring Data JPA's query method conventions, allowing
     * the framework to automatically generate the appropriate query.
     * </p>
     *
     * <p>
     * Typical usage example:
     * <pre>
     *     List&lt;Book&gt; books = bookRepository.findByIsbn("123-456-789");
     * </pre>
     * </p>
     *
     * @param isbn The ISBN of the books to find.
     * @return A list of books with the specified ISBN.
     * @throws IllegalArgumentException if the given {@literal isbn} is {@literal null}.
     */
    List<Book> findByIsbn(String isbn);
}
