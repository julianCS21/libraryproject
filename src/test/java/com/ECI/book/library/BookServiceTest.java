package com.ECI.book.library;

import com.ECI.book.library.application.BookService;
import com.ECI.book.library.domain.exceptions.LendsException;
import com.ECI.book.library.domain.exceptions.LibraryException;
import com.ECI.book.library.domain.exceptions.UsersException;
import com.ECI.book.library.domain.model.*;
import com.ECI.book.library.domain.repository.BookRepository;
import com.ECI.book.library.domain.repository.LendRepository;
import com.ECI.book.library.domain.repository.UserRepository;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LendRepository lendRepository;

    @InjectMocks
    @Autowired
    private BookService bookService;

    private final String bookId = "2";
    private final String userId = "3";

    private final Book book = new Book(bookId,"",new ArrayList<>(),new ArrayList<>(),5);

    private final User user = new User(userId,"Ivan","Sanchez",39,"1016835109", DocType.CC);

    @Test(expected = LendsException.BookNotFoundException.class)
    public void shouldnotLendBookBecauseNotExists() throws LibraryException {
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
        bookService.lendBook(bookId,userId);
    }

    @Test(expected = LendsException.BookNotAvailableException.class)
    public void shouldnotLendBookBecauseNotAvailable() throws LibraryException {
        book.setAmount(0);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        bookService.lendBook(bookId,userId);
    }

    @Test(expected = LendsException.PendingPenalitiesException.class)
    public void shouldnotLendBookBecauseOfPenalty() throws LibraryException{
        List<Lend> lends = new ArrayList<>();
        lends.add(new Lend(new Penalty(new Date(), 4000), book, user));
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(lendRepository.findByUserId(userId)).thenReturn(lends);

        bookService.lendBook(bookId,userId);
    }

    @Test(expected = UsersException.UserNotFoundException.class)
    public void shouldnotLendBookBecauseUserNotExist() throws LibraryException{
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        bookService.lendBook(bookId,userId);
    }

    @Test
    public void shouldInventaryChangeIfBookWasLent() throws LibraryException{
        int oldAmount = book.getAmount();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(lendRepository.findByUserId(userId)).thenReturn(new ArrayList<>());
        bookService.lendBook(bookId,userId);
        assertEquals(oldAmount, book.getAmount() + 1);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void shouldLendBook() throws LibraryException{
        System.out.println(book.getAmount());
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        bookService.lendBook(bookId,userId);
    }



}
