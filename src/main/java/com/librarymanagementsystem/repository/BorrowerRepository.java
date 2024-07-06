package com.librarymanagementsystem.repository;

import com.librarymanagementsystem.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Borrower} entities.
 * <p>
 * This interface extends the {@link JpaRepository} interface to provide
 * CRUD (Create, Read, Update, Delete) operations for the {@link Borrower} entity.
 * It is annotated with {@link Repository} to indicate that it is a Spring Data repository.
 * </p>
 *
 * <p>
 * The {@link JpaRepository} provides several methods for interacting with the database,
 * including methods for saving, deleting, and finding entities. Additional query methods
 * can be defined by simply declaring them in this interface.
 * </p>
 *
 * <p>
 * Typical usage example:
 * <pre>
 *     List&lt;Borrower&gt; allBorrowers = borrowerRepository.findAll();
 *     Optional&lt;Borrower&gt; borrower = borrowerRepository.findById(1L);
 *     borrowerRepository.save(new Borrower());
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
 * @see com.librarymanagementsystem.model.Borrower
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.stereotype.Repository
 */
@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
}
