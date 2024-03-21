package com.ECI.book.library;

import com.ECI.book.library.application.BookService;
import com.ECI.book.library.domain.exceptions.LendsException;
import com.ECI.book.library.domain.exceptions.UsersException;
import com.ECI.book.library.domain.model.Book;
import com.ECI.book.library.domain.model.DocType;
import com.ECI.book.library.domain.model.Lend;
import com.ECI.book.library.domain.model.User;
import com.ECI.book.library.domain.repository.BookRepository;
import com.ECI.book.library.domain.repository.UserRepository;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    BookService bookService;

    private final String bookId = "2";
    private final String userId = "3";

    private final Book book = new Book(bookId,"",new ArrayList<>(),new ArrayList<>(),5);
    private final Book bookExpected = new Book(bookId,"",new ArrayList<>(),new ArrayList<>(),4);

    private final User user = new User(userId,"Ivan","Sanchez",39,"1016835109", DocType.CC);

    @Before
    public void setUp() {
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book)).thenReturn(Optional.of(bookExpected));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    }

    @Test(expected = LendsException.BookNotFoundException.class)
    public void shouldnotLendBookBecauseNotExists(){
        //Act


        bookService.lendBook(bookId,userId);

    }

    @Test(expected = LendsException.BookNotAvailableException.class)
    public void shouldnotLendBookBecauseNotAvailable(){

        bookService.lendBook(bookId,userId);



    }

    @Test(expected = LendsException.PendingPenalitiesException.class)
    public void shouldnotLendBookBecauseOfPenalty(){
        bookService.lendBook(bookId,userId);

    }

    @Test(expected = UsersException.UserNotFoundException.class)
    public void shouldnotLendBookBecauseUserNotExist(){
        bookService.lendBook(bookId,userId);

    }

    @Test
    public void shouldInventaryChangeIfBookWasLent(){
        Book oldBook =  bookService.getBook(bookId);
        bookService.lendBook(bookId,userId);
        Book newBook = bookService.getBook(bookId);
        assertEquals(oldBook.getAmount(), newBook.getAmount() - 1);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void shouldLendBook(){
        bookService.lendBook(bookId,userId);
    }



}
