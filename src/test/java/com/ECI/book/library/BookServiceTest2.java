package com.ECI.book.library;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.ECI.book.library.application.BookService;
import com.ECI.book.library.domain.exceptions.LendsException;
import com.ECI.book.library.domain.exceptions.LibraryException;
import com.ECI.book.library.domain.exceptions.UsersException;
import com.ECI.book.library.domain.model.Book;
import com.ECI.book.library.domain.model.Lend;
import com.ECI.book.library.domain.model.User;
import com.ECI.book.library.infraestructure.repository.BookRepository;
import com.ECI.book.library.infraestructure.repository.LendRepository;
import com.ECI.book.library.infraestructure.repository.UserRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest2 {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private LendRepository lendRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;
    private User user;
    private Lend lend;

    @Before
    public void setUp() {
        book = new Book("1", "Test Book", null, null, 10);
        user = new User("1", "John", "Doe", 30, "123456", null);
        lend = new Lend("1", user, book, null);
    }

    @Test
    public void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
        List<Book> books = bookService.getAll();
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
        verify(bookRepository).findAll();
    }

    @Test(expected = LendsException.BookNotFoundException.class)
    public void testGetBookNotFound() throws LibraryException {
        when(bookRepository.findById("1")).thenReturn(Optional.empty());
        bookService.getBook("1");
    }

    @Test
    public void testGetBook() throws LibraryException {
        when(bookRepository.findById("1")).thenReturn(Optional.of(book));
        Book foundBook = bookService.getBook("1");
        assertNotNull(foundBook);
        assertEquals("Test Book", foundBook.getTitle());
    }

    @Test(expected = LendsException.BookNotAvailableException.class)
    public void testLendBookNotAvailable() throws LibraryException {
        book.setAmount(0);
        when(bookRepository.findById("1")).thenReturn(Optional.of(book));
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        bookService.lendBook("1", "1");
    }

    @Test(expected = UsersException.UserNotFoundException.class)
    public void testLendBookUserNotFound() throws LibraryException {
        when(bookRepository.findById("1")).thenReturn(Optional.of(book));
        when(userRepository.findById("1")).thenReturn(Optional.empty());
        bookService.lendBook("1", "1");
    }

    @Test
    public void testLendBookSuccessful() throws LibraryException {
        when(bookRepository.findById("1")).thenReturn(Optional.of(book));
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        when(lendRepository.findByUserId("1")).thenReturn(Arrays.asList());
        bookService.lendBook("1", "1");
        verify(lendRepository).save(any(Lend.class));
        verify(bookRepository).save(book);
        assertEquals(9, book.getAmount());
    }
}
