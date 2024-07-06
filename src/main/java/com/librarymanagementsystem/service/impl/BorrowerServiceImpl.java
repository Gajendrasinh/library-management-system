package com.librarymanagementsystem.service.impl;

import com.librarymanagementsystem.dto.BorrowerDto;
import com.librarymanagementsystem.exception.NotFoundException;
import com.librarymanagementsystem.mapper.LibraryMapper;
import com.librarymanagementsystem.model.Borrower;
import com.librarymanagementsystem.repository.BorrowerRepository;
import com.librarymanagementsystem.service.BorrowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link BorrowerService} interface.
 * This service handles the operations related to borrowers in the library system.
 * It uses {@link BorrowerRepository} for data persistence and {@link LibraryMapper}
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
public class BorrowerServiceImpl implements BorrowerService {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private LibraryMapper mapper;

    /**
     * Creates a new borrower in the library system.
     * <p>
     * This method maps the given {@link BorrowerDto} to a {@link Borrower} entity,
     * saves it using the repository, and then maps the saved entity back to a DTO.
     * </p>
     * @param borrowerDto the data transfer object containing the borrower's details
     * @return the created {@link BorrowerDto} with updated information (e.g., ID)
     */
    @Override
    public BorrowerDto createBorrower(BorrowerDto borrowerDto) {
        log.info("Creating new borrower: {}", borrowerDto);

        Borrower borrower = mapper.buildBorrowerFromDto(borrowerDto);
        borrower = borrowerRepository.save(borrower);

        BorrowerDto result = mapper.buildBorrowerDtoFromEntity(borrower);
        log.info("Created borrower: {}", result);

        return result;
    }

    /**
     * Retrieves all borrowers from the database.
     *
     * @return a list of {@link BorrowerDto} objects representing all borrowers
     */
    @Override
    public List<BorrowerDto> getAllBorrowers() {
        List<Borrower> borrowers = borrowerRepository.findAll();
        return borrowers.stream()
                .map(mapper::buildBorrowerDtoFromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a borrower by their unique identifier.
     *
     * @param id the identifier of the borrower to retrieve
     * @return the {@link BorrowerDto} object representing the retrieved borrower
     * @throws NotFoundException if no borrower exists with the specified ID
     */
    @Override
    public BorrowerDto getBorrowerById(Long id) {
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Borrower not found with id: " + id));
        return mapper.buildBorrowerDtoFromEntity(borrower);
    }

    /**
     * Updates an existing borrower's details.
     *
     * @param id          the identifier of the borrower to update
     * @param borrowerDto the {@link BorrowerDto} object containing updated details
     * @return the updated {@link BorrowerDto} object
     * @throws NotFoundException if no borrower exists with the specified ID
     */
    @Override
    public BorrowerDto updateBorrower(Long id, BorrowerDto borrowerDto) {
        Borrower existingBorrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Borrower not found with id: " + id));

        existingBorrower.setName(borrowerDto.getName());
        existingBorrower.setEmail(borrowerDto.getEmail());

        borrowerRepository.save(existingBorrower);

        return mapper.buildBorrowerDtoFromEntity(existingBorrower);
    }

    /**
     * Deletes a borrower by their unique identifier.
     *
     * @param id the identifier of the borrower to delete
     * @throws NotFoundException if no borrower exists with the specified ID
     */
    @Override
    public void deleteBorrower(Long id) {
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Borrower not found with id: " + id));
        borrowerRepository.delete(borrower);
    }
}
