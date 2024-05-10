package com.ECI.book.library.infraestructure.inbound;

import com.ECI.book.library.application.BookService;
import com.ECI.book.library.domain.DTO.LendDTO;
import com.ECI.book.library.domain.exceptions.LendsException;
import com.ECI.book.library.domain.exceptions.LibraryException;
import com.ECI.book.library.domain.exceptions.UsersException;
import com.ECI.book.library.domain.model.Book;
import com.ECI.book.library.domain.model.Lend;
import com.ECI.book.library.infraestructure.repository.BookRepository;
import com.ECI.book.library.infraestructure.repository.LendRepository;
import com.ECI.book.library.infraestructure.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private LendRepository lendRepository;


    @MockBean
    private BookService bookService;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void getAllBooks_success() throws Exception {
        when(bookService.getAll()).thenReturn(Arrays.asList(new Book(), new Book()));

        mockMvc.perform(get("/books/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void lendBook_success() throws Exception {
        LendDTO lendDTO = new LendDTO("bookId1", "userId1");
        Lend lend = new Lend(); // Supone que has configurado el objeto `Lend` correctamente

        when(bookService.lendBook(lendDTO.getBookId(), lendDTO.getUserId())).thenReturn(lend);

        mockMvc.perform(post("/books/lend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookId\":\"bookId1\", \"userId\":\"userId1\"}"))
                .andExpect(status().isOk());
    }




    @Test
    public void saveBook_success() throws Exception {
        Book book = new Book("1", "Book One", null, null, 1);
        when(bookService.save(book)).thenReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\", \"title\":\"Book One\", \"edition\":1}"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveBook_fail() throws Exception {
        Book book = new Book("1", "Book One", null, null, 1);
        when(bookService.save(any(Book.class))).thenThrow(RuntimeException.class);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\", \"title\":\"Book One\", \"edition\":1}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void lendBook_bookNotFound_fail() throws Exception {
        when(bookService.lendBook("invalidBookId", "validUserId"))
                .thenThrow(new LendsException.BookNotFoundException());

        mockMvc.perform(post("/books/lend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookId\":\"invalidBookId\", \"userId\":\"validUserId\"}"))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void lendBook_bookNotAvailable_fail() throws Exception {
        when(bookService.lendBook("unavailableBookId", "userId"))
                .thenThrow(new LendsException.BookNotAvailableException());

        mockMvc.perform(post("/books/lend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookId\":\"unavailableBookId\", \"userId\":\"userId\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void lendBook_userNotFound_fail() throws Exception {
        when(bookService.lendBook("bookId", "nonexistentUserId"))
                .thenThrow(new UsersException.UserNotFoundException());

        mockMvc.perform(post("/books/lend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookId\":\"bookId\", \"userId\":\"nonexistentUserId\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void lendBook_pendingPenalties_fail() throws Exception {
        when(bookService.lendBook("bookId", "userIdWithPenalties"))
                .thenThrow(new LendsException.PendingPenalitiesException());

        mockMvc.perform(post("/books/lend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookId\":\"bookId\", \"userId\":\"userIdWithPenalties\"}"))
                .andExpect(status().isBadRequest());
    }



}
