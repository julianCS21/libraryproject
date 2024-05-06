package com.ECI.book.library;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ECI.book.library.application.BookService;
import com.ECI.book.library.domain.exceptions.*;
import com.ECI.book.library.domain.model.*;
import com.ECI.book.library.infraestructure.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private LendRepository lendRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void getAllBooks() {
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(new Book()));
        assertNotNull(bookService.getAll());
        verify(bookRepository).findAll();
    }

    @Test
    public void getBookFound() throws LibraryException {
        when(bookRepository.findById("1")).thenReturn(Optional.of(new Book()));
        assertNotNull(bookService.getBook("1"));
    }

    @Test
    public void getBookNotFound() {
        when(bookRepository.findById("2")).thenReturn(Optional.empty());
        assertThrows(LendsException.BookNotFoundException.class, () -> bookService.getBook("2"));
    }

    @Test
    public void lendBookBookNotAvailable() {
        Book book = new Book("1", null, null, null, 0);
        when(bookRepository.findById("1")).thenReturn(Optional.of(book));
        assertThrows(LendsException.BookNotAvailableException.class, () -> bookService.lendBook("1", "user1"));
    }

    @Test
    public void lendBookUserNotFound() {
        Book book = new Book("1", null, null, null, 10);
        when(bookRepository.findById("1")).thenReturn(Optional.of(book));
        when(userRepository.findById("user1")).thenReturn(Optional.empty());
        assertThrows(UsersException.UserNotFoundException.class, () -> bookService.lendBook("1", "user1"));
    }

    @Test
    public void lendBookPendingPenalties() {
        Book book = new Book("1", null, null, null, 10);
        User user = new User("user1", null, null, 0, null, null);
        Penalty penalty = new Penalty(new Date(System.currentTimeMillis() - 10000), 50); // Past date
        Lend lend = new Lend("lend1", user, book, penalty);

        when(bookRepository.findById("1")).thenReturn(Optional.of(book));
        when(userRepository.findById("user1")).thenReturn(Optional.of(user));
        when(lendRepository.findByUserId("user1")).thenReturn(Collections.singletonList(lend));

        assertThrows(LendsException.PendingPenalitiesException.class, () -> bookService.lendBook("1", "user1"));
    }

    @Test
    public void lendBookSuccess() throws LibraryException {
        Book book = new Book("1", null, null, null, 10);
        User user = new User("user1", null, null, 0, null, null);
        when(bookRepository.findById("1")).thenReturn(Optional.of(book));
        when(userRepository.findById("user1")).thenReturn(Optional.of(user));
        when(lendRepository.findByUserId("user1")).thenReturn(Collections.emptyList());

        Lend lend = bookService.lendBook("1", "user1");
        assertNotNull(lend);
        assertEquals("1", lend.getBook().getId());
        assertEquals("user1", lend.getUser().getId());
        verify(lendRepository).save(any(Lend.class));
        verify(bookRepository).save(book);
        assertEquals(9, book.getAmount());
    }
}
