package com.librarymanagementsystem.service;

import com.librarymanagementsystem.dto.BorrowerDto;
import com.librarymanagementsystem.exception.NotFoundException;
import com.librarymanagementsystem.mapper.LibraryMapper;
import com.librarymanagementsystem.model.Borrower;
import com.librarymanagementsystem.repository.BorrowerRepository;
import com.librarymanagementsystem.service.impl.BorrowerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BorrowerServiceTest {

    @InjectMocks
    private BorrowerServiceImpl borrowerService;

    @Mock
    private BorrowerRepository borrowerRepository;

    @Mock
    private LibraryMapper libraryMapper;

    // Test for createBorrower method
    @Test
    void testCreateBorrower() {
        BorrowerDto borrowerDto = getBorrowerDto();
        Borrower borrower = getBorrower();
        Mockito.when(libraryMapper.buildBorrowerFromDto(borrowerDto)).thenReturn(borrower);
        Mockito.when(borrowerRepository.save(borrower)).thenReturn(borrower);
        Mockito.when(libraryMapper.buildBorrowerDtoFromEntity(borrower)).thenReturn(borrowerDto);

        BorrowerDto createdBorrower = borrowerService.createBorrower(borrowerDto);

        assertEquals(borrowerDto.getName(), createdBorrower.getName());
        Mockito.verify(libraryMapper, Mockito.times(1)).buildBorrowerFromDto(borrowerDto);
        Mockito.verify(borrowerRepository, Mockito.times(1)).save(borrower);
        Mockito.verify(libraryMapper, Mockito.times(1)).buildBorrowerDtoFromEntity(borrower);
    }

    // Test for getAllBorrowers method
    @Test
    void testGetAllBorrowers() {
        List<Borrower> borrowers = Collections.singletonList(getBorrower());
        BorrowerDto borrowerDto = getBorrowerDto();
        Mockito.when(borrowerRepository.findAll()).thenReturn(borrowers);
        Mockito.when(libraryMapper.buildBorrowerDtoFromEntity(Mockito.any())).thenReturn(borrowerDto);

        List<BorrowerDto> borrowerDtos = borrowerService.getAllBorrowers();

        assertEquals(1, borrowerDtos.size());
        assertEquals(borrowerDto.getName(), borrowerDtos.get(0).getName());
        Mockito.verify(borrowerRepository, Mockito.times(1)).findAll();
    }

    // Test for getBorrowerById method
    @Test
    void testGetBorrowerById() {
        Borrower borrower = getBorrower();
        BorrowerDto borrowerDto = getBorrowerDto();
        Mockito.when(borrowerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(borrower));
        Mockito.when(libraryMapper.buildBorrowerDtoFromEntity(borrower)).thenReturn(borrowerDto);

        BorrowerDto foundBorrower = borrowerService.getBorrowerById(1L);

        assertEquals(borrowerDto.getName(), foundBorrower.getName());
        Mockito.verify(borrowerRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(libraryMapper, Mockito.times(1)).buildBorrowerDtoFromEntity(borrower);
    }

    // Test for getBorrowerById method throwing NotFoundException
    @Test
    void testGetBorrowerByIdNotFound() {
        Mockito.when(borrowerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> borrowerService.getBorrowerById(1L));
    }

    // Test for updateBorrower method
    @Test
    void testUpdateBorrower() {
        Borrower existingBorrower = getBorrower();
        BorrowerDto borrowerDto = getBorrowerDto();
        Mockito.when(borrowerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(existingBorrower));
        Mockito.when(borrowerRepository.save(Mockito.any())).thenReturn(existingBorrower);
        Mockito.when(libraryMapper.buildBorrowerDtoFromEntity(existingBorrower)).thenReturn(borrowerDto);

        BorrowerDto updatedBorrower = borrowerService.updateBorrower(1L, borrowerDto);

        assertEquals(borrowerDto.getName(), updatedBorrower.getName());
        Mockito.verify(borrowerRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(borrowerRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(libraryMapper, Mockito.times(1)).buildBorrowerDtoFromEntity(existingBorrower);
    }

    // Test for updateBorrower method throwing NotFoundException
    @Test
    void testUpdateBorrowerNotFound() {
        Mockito.when(borrowerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> borrowerService.updateBorrower(1L, getBorrowerDto()));
    }

    // Test for deleteBorrower method
    @Test
    void testDeleteBorrower() {
        Borrower borrower = getBorrower();
        Mockito.when(borrowerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(borrower));
        Mockito.doNothing().when(borrowerRepository).delete(Mockito.any());

        borrowerService.deleteBorrower(1L);

        Mockito.verify(borrowerRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(borrowerRepository, Mockito.times(1)).delete(Mockito.any());
    }

    // Test for deleteBorrower method throwing NotFoundException
    @Test
    void testDeleteBorrowerNotFound() {
        Mockito.when(borrowerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> borrowerService.deleteBorrower(1L));
    }

    // Helper methods for creating sample Borrower and BorrowerDto
    private Borrower getBorrower() {
        return Borrower.builder()
                .id(1L)
                .name("Gajendrasinh Zala")
                .email("gajendrasinh.zala93@gmail.com")
                .books(Collections.emptySet())
                .build();
    }

    private BorrowerDto getBorrowerDto() {
        return BorrowerDto.builder()
                .id(1L)
                .name("Gajendrasinh Zala")
                .email("gajendrasinh.zala93@gmail.com")
                .build();
    }
}
