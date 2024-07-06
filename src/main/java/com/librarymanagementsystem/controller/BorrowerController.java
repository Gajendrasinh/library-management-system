package com.librarymanagementsystem.controller;

import com.librarymanagementsystem.config.APIResourcePaths;
import com.librarymanagementsystem.dto.ApiResponse;
import com.librarymanagementsystem.dto.BorrowerDto;
import com.librarymanagementsystem.service.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing borrower-related operations in the library system.
 * This controller provides endpoints for registering, retrieving, updating, and deleting borrowers.
 * <p>
 * Author: Gajendrasinh Zala
 * Email: gajendrasinh.zala93@gmail.com
 * Github: https://github.com/Gajendrasinh
 * </p>
 */
@RestController
@RequestMapping(value = APIResourcePaths.BORROWER_URL)
public class BorrowerController {


    /**
     * Service responsible for borrower-related operations.
     */
    @Autowired
    BorrowerService borrowerService;

    /**
     * Endpoint to register a new borrower in the library system.
     * <p>
     * This endpoint allows the creation of a new borrower by providing borrower details.
     * The borrower data is validated before saving.
     * </p>
     *
     * @param borrowerDto the BorrowerDto object containing borrower information
     * @return the created BorrowerDto object with the registered borrower details
     */
    @PostMapping(value = APIResourcePaths.CREATE_BORROWER_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public BorrowerDto createBorrower(@Valid @RequestBody BorrowerDto borrowerDto) {
        return borrowerService.createBorrower(borrowerDto);
    }

    /**
     * Endpoint to retrieve all borrowers in the library system.
     *
     * @return an ApiResponse containing a list of BorrowerDto objects
     */
    @GetMapping(value = APIResourcePaths.GET_ALL_BORROWER_URL)
    public ApiResponse<List<BorrowerDto>> getAllBorrowers() {
        List<BorrowerDto> borrowers = borrowerService.getAllBorrowers();
        return new ApiResponse<>("success", "Borrowers retrieved successfully", borrowers);
    }

    /**
     * Endpoint to retrieve a specific borrower by their unique identifier.
     *
     * @param borrowerId the identifier of the borrower to retrieve
     * @return an ApiResponse containing the BorrowerDto object representing the retrieved borrower
     */
    @GetMapping(value = APIResourcePaths.GET_BORROWER_URL)
    public ApiResponse<BorrowerDto> getBorrowerById(@PathVariable Long borrowerId) {
        BorrowerDto borrowerDto = borrowerService.getBorrowerById(borrowerId);
        return new ApiResponse<>("success", "Borrower retrieved successfully", borrowerDto);
    }

    /**
     * Endpoint to update an existing borrower's details.
     *
     * @param borrowerId          the identifier of the borrower to update
     * @param borrowerDto the BorrowerDto object containing updated borrower details
     * @return an ApiResponse containing the updated BorrowerDto object
     */
    @PutMapping(APIResourcePaths.UPDATE_BORROWER_URL)
    public ApiResponse<BorrowerDto> updateBorrower(@PathVariable Long borrowerId, @Valid @RequestBody BorrowerDto borrowerDto) {
        BorrowerDto updatedBorrower = borrowerService.updateBorrower(borrowerId, borrowerDto);
        return new ApiResponse<>("success", "Borrower updated successfully", updatedBorrower);
    }

    /**
     * Endpoint to delete a borrower by their unique identifier.
     *
     * @param borrowerId the identifier of the borrower to delete
     * @return an ApiResponse indicating the deletion success message
     */
    @DeleteMapping(APIResourcePaths.DELETE_BORROWER_URL)
    public ApiResponse<String> deleteBorrower(@PathVariable Long borrowerId) {
        borrowerService.deleteBorrower(borrowerId);
        return new ApiResponse<>("success", "Borrower deleted successfully", null);
    }
}
