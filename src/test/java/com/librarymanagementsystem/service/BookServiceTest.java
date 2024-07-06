package com.librarymanagementsystem.service;

import com.librarymanagementsystem.dto.BookDto;
import com.librarymanagementsystem.exception.AlreadyExistException;
import com.librarymanagementsystem.exception.NotFoundException;
import com.librarymanagementsystem.mapper.LibraryMapper;
import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.model.Borrower;
import com.librarymanagementsystem.repository.BookRepository;
import com.librarymanagementsystem.repository.BorrowerRepository;
import com.librarymanagementsystem.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl service;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowerRepository borrowerRepository;

    @Mock
    private LibraryMapper mapper;

    // Test for createBook method
    @Test
    void testCreateBook() {
        Mockito.when(bookRepository.findByIsbn(Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(getBook());
        Mockito.when(mapper.buildBookDtoFromEntity(Mockito.any())).thenReturn(getBookDto());

        BookDto bookDto = service.createBook(getBookDto());

        Assertions.assertEquals("Gajendrasinh Zala", bookDto.getAuthor());
        Assertions.assertEquals("Core Java", bookDto.getTitle());
        Assertions.assertEquals("881", bookDto.getIsbn());

        Mockito.verify(bookRepository, Mockito.times(1)).findByIsbn(Mockito.anyString());
        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.any());
    }

    // Test for AlreadyExistException in createBook
    @Test
    void testCreateBookThrowAlreadyExistException() {
        Mockito.when(bookRepository.findByIsbn(Mockito.anyString())).thenReturn(Collections.singletonList(getBook()));
        BookDto bookDto = getBookDto();
        bookDto.setAuthor("Test");

        Assertions.assertThrows(AlreadyExistException.class, () -> service.createBook(bookDto));
    }

    // Test for getAllBooks method
    @Test
    void testGetAllBooks() {
        List<Book> books = List.of(getBook());
        Mockito.when(bookRepository.findAll()).thenReturn(books);
        Mockito.when(mapper.buildBookDtoFromEntity(Mockito.any())).thenReturn(getBookDto());

        List<BookDto> bookDtos = service.getAllBooks();

        Assertions.assertEquals(1, bookDtos.size());
        Assertions.assertEquals("Gajendrasinh Zala", bookDtos.get(0).getAuthor());

        Mockito.verify(bookRepository, Mockito.times(1)).findAll();
    }

    // Test for getBookById method
    @Test
    void testGetBookById() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getBook()));
        Mockito.when(mapper.buildBookDtoFromEntity(Mockito.any())).thenReturn(getBookDto());

        BookDto bookDto = service.getBookById(12345L);

        Assertions.assertEquals("Gajendrasinh Zala", bookDto.getAuthor());
        Mockito.verify(bookRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    // Test for NotFoundException in getBookById
    @Test
    void testGetBookByIdNotFound() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> service.getBookById(12345L));
    }

    // Test for updateBook method
    @Test
    void testUpdateBook() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getBook()));
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(getBook());
        Mockito.when(mapper.buildBookDtoFromEntity(Mockito.any())).thenReturn(getBookDto());

        BookDto updatedBookDto = service.updateBook(12345L, getBookDto());

        Assertions.assertEquals("Gajendrasinh Zala", updatedBookDto.getAuthor());
        Mockito.verify(bookRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.any());
    }

    // Test for NotFoundException in updateBook
    @Test
    void testUpdateBookNotFound() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> service.updateBook(12345L, getBookDto()));
    }

    // Test for deleteBook method
    @Test
    void testDeleteBook() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getBook()));
        Mockito.doNothing().when(bookRepository).delete(Mockito.any());

        service.deleteBook(12345L);

        Mockito.verify(bookRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(bookRepository, Mockito.times(1)).delete(Mockito.any());
    }

    // Test for NotFoundException in deleteBook
    @Test
    void testDeleteBookNotFound() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> service.deleteBook(12345L));
    }

    // Test for borrowedBook method
    @Test
    void testBorrowedBook() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getBook()));
        Mockito.when(borrowerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getBorrower()));
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(getBook());
        Mockito.when(borrowerRepository.save(Mockito.any())).thenReturn(getBorrower());
        Mockito.when(mapper.buildBookDtoFromEntity(Mockito.any())).thenReturn(getBookDto());

        BookDto borrowedBookDto = service.borrowedBook(12345L, 67890L);

        Assertions.assertEquals("Gajendrasinh Zala", borrowedBookDto.getAuthor());
        Mockito.verify(bookRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(borrowerRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(borrowerRepository, Mockito.times(1)).save(Mockito.any());
    }

    // Test for NotFoundException in borrowedBook
    @Test
    void testBorrowedBookNotFound() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> service.borrowedBook(12345L, 67890L));
    }

    // Test for AlreadyExistException in borrowedBook
    @Test
    void testBorrowedBookAlreadyBorrowed() {
        Borrower borrower = getBorrower();
        borrower.getBooks().add(getBook());
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getBook()));
        Mockito.when(borrowerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(borrower));

        Assertions.assertThrows(AlreadyExistException.class, () -> service.borrowedBook(12345L, 67890L));
    }

    // Test for returnBook method
    @Test
    void testReturnBook() {
        Borrower borrower = getBorrower();
        borrower.getBooks().add(getBook());
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getBook()));
        Mockito.when(borrowerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(borrower));
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(getBook());
        Mockito.when(borrowerRepository.save(Mockito.any())).thenReturn(getBorrower());
        Mockito.when(mapper.buildBookDtoFromEntity(Mockito.any())).thenReturn(getBookDto());

        BookDto returnedBookDto = service.returnBook(12345L, 67890L);

        Assertions.assertEquals("Gajendrasinh Zala", returnedBookDto.getAuthor());
        Mockito.verify(bookRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(borrowerRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(borrowerRepository, Mockito.times(1)).save(Mockito.any());
    }

    // Test for NotFoundException in returnBook
    @Test
    void testReturnBookNotFound() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> service.returnBook(12345L, 67890L));
    }

    // Test for AlreadyExistException in returnBook
    @Test
    void testReturnBookNotBorrowed() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getBook()));
        Mockito.when(borrowerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getBorrower()));

        Assertions.assertThrows(AlreadyExistException.class, () -> service.returnBook(12345L, 67890L));
    }

    private BookDto getBookDto() {
        return BookDto.builder().author("Gajendrasinh Zala").title("Java clean architecture").isbn("881").build();
    }

    private Book getBook() {
        return Book.builder().author("Gajendrasinh Zala").title("Java clean architecture").isbn("881").id(12345L).build();
    }

    private Borrower getBorrower() {
        return Borrower.builder().id(67890L).name("John Doe").books(Collections.emptySet()).build();
    }
}
