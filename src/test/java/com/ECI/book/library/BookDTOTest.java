package com.ECI.book.library;

import com.ECI.book.library.domain.DTO.AuthorDTO;
import com.ECI.book.library.domain.DTO.BookDTO;
import com.ECI.book.library.domain.DTO.CategoryDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BookDTOTest {

    @Test
    void testNoArgsConstructor() {
        BookDTO bookDTO = new BookDTO();
        assertNull(bookDTO.getId());
        assertNull(bookDTO.getTitle());
        assertNull(bookDTO.getAuthors());
        assertNull(bookDTO.getCategories());
        assertEquals(0, bookDTO.getAmount());
    }

    @Test
    void testAllArgsConstructor() {
        AuthorDTO authorDTO = new AuthorDTO("authorId", "authorName");
        CategoryDTO categoryDTO = new CategoryDTO("categoryId", "categoryName");
        List<AuthorDTO> authors = List.of(authorDTO);
        List<CategoryDTO> categories = List.of(categoryDTO);

        BookDTO bookDTO = new BookDTO("1", "Book One", authors, categories, 5);

        assertEquals("1", bookDTO.getId());
        assertEquals("Book One", bookDTO.getTitle());
        assertEquals(authors, bookDTO.getAuthors());
        assertEquals(categories, bookDTO.getCategories());
        assertEquals(5, bookDTO.getAmount());
    }

    @Test
    void testSettersAndGetters() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId("1");
        bookDTO.setTitle("Book Title");
        bookDTO.setAmount(10);

        assertEquals("1", bookDTO.getId());
        assertEquals("Book Title", bookDTO.getTitle());
        assertEquals(10, bookDTO.getAmount());
    }

    @Test
    void testBuilder() {
        AuthorDTO authorDTO = new AuthorDTO("authorId", "authorName");
        CategoryDTO categoryDTO = new CategoryDTO("categoryId", "categoryName");
        List<AuthorDTO> authors = List.of(authorDTO);
        List<CategoryDTO> categories = List.of(categoryDTO);

        BookDTO bookDTO = BookDTO.builder()
                .id("1")
                .title("Book One")
                .authors(authors)
                .categories(categories)
                .amount(5)
                .build();

        assertEquals("1", bookDTO.getId());
        assertEquals("Book One", bookDTO.getTitle());
        assertEquals(authors, bookDTO.getAuthors());
        assertEquals(categories, bookDTO.getCategories());
        assertEquals(5, bookDTO.getAmount());
    }
}
