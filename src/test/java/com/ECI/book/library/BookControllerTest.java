package com.ECI.book.library;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

import com.ECI.book.library.application.BookService;
import com.ECI.book.library.domain.exceptions.LibraryException;
import com.ECI.book.library.domain.model.Book;
import com.ECI.book.library.domain.model.Lend;
import com.ECI.book.library.domain.model.User;
import com.ECI.book.library.infraestructure.inbound.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void getAllBooks() throws Exception {
        List<Book> allBooks = Arrays.asList(new Book("1", "Book One", null, null, 5));
        given(bookService.getAll()).willReturn(allBooks);

        mockMvc.perform(get("/books/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].title").value("Book One"));
    }

    @Test
    public void lendBookSuccess() throws Exception {
        Lend lend = new Lend("1", new User(), new Book(), null);
        given(bookService.lendBook("1", "1")).willReturn(lend);

        mockMvc.perform(post("/books/lend")
                        .param("bookId", "1")
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void lendBookFailure() throws Exception {
        given(bookService.lendBook("2", "2")).willThrow(new LibraryException("Error"));

        mockMvc.perform(post("/books/lend")
                        .param("bookId", "2")
                        .param("userId", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
