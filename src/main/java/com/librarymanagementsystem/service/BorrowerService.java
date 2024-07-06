package com.librarymanagementsystem.service;

import com.librarymanagementsystem.dto.BorrowerDto;

import java.util.List;

/**
 * Service interface for managing borrowers in the library system.
 * <p>
 * This interface defines the contract for operations related to borrowers,
 * such as creating, retrieving, updating, and deleting borrowers. Implementations
 * of this interface are responsible for handling borrower-related logic.
 * </p>
 *
 * <p>
 * Typical usage example:
 * <pre>
 *     BorrowerDto newBorrower = new BorrowerDto();
 *     newBorrower.setName("John Doe");
 *     newBorrower.setEmail("john.doe@example.com");
 *     BorrowerDto createdBorrower = borrowerService.createBorrower(newBorrower);
 * </pre>
 * </p>
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 * @see com.librarymanagementsystem.service.impl.BorrowerServiceImpl
 * @see com.librarymanagementsystem.dto.BorrowerDto
 */
public interface BorrowerService {

    /**
     * Creates a new borrower in the library system.
     *
     * @param borrowerDto the data transfer object containing the borrower's details
     * @return the created {@link BorrowerDto} with updated information (e.g., ID)
     */
    BorrowerDto createBorrower(BorrowerDto borrowerDto);

    /**
     * Retrieves all borrowers in the library system.
     *
     * @return a list of {@link BorrowerDto} objects representing all borrowers
     */
    List<BorrowerDto> getAllBorrowers();

    /**
     * Retrieves a borrower by their unique identifier.
     *
     * @param id the identifier of the borrower to retrieve
     * @return the {@link BorrowerDto} object representing the retrieved borrower
     */
    BorrowerDto getBorrowerById(Long id);

    /**
     * Updates an existing borrower's details.
     *
     * @param id          the identifier of the borrower to update
     * @param borrowerDto the {@link BorrowerDto} object containing updated details
     * @return the updated {@link BorrowerDto} object
     */
    BorrowerDto updateBorrower(Long id, BorrowerDto borrowerDto);

    /**
     * Deletes a borrower by their unique identifier.
     *
     * @param id the identifier of the borrower to delete
     */
    void deleteBorrower(Long id);
}
