package com.ECI.book.library;

import static org.junit.jupiter.api.Assertions.*;

import com.ECI.book.library.domain.model.Author;
import com.ECI.book.library.domain.model.Book;
import com.ECI.book.library.domain.model.Category;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class BookTest {
    @Test
    public void testNoArgsConstructor() {
        Book book = new Book();
        assertNull(book.getId());
        assertNull(book.getTitle());
        assertNull(book.getAuthors());
        assertNull(book.getCategories());
        assertEquals(0, book.getAmount());
    }

    @Test
    public void testAllArgsConstructor() {
        List<Author> authors = Arrays.asList(new Author("1", "Author Bio", 45, null));
        List<Category> categories = Arrays.asList(new Category("1", "Fiction", "Fictional books"));
        Book book = new Book("1", "Harry Potter", authors, categories, 100);
        assertEquals("1", book.getId());
        assertEquals("Harry Potter", book.getTitle());
        assertEquals(authors, book.getAuthors());
        assertEquals(categories, book.getCategories());
        assertEquals(100, book.getAmount());
    }

    @Test
    public void testBuilder() {
        Book book = Book.builder()
                .id("2")
                .title("Lord of the Rings")
                .authors(Arrays.asList(new Author()))
                .categories(Arrays.asList(new Category()))
                .amount(50)
                .build();
        assertEquals("2", book.getId());
        assertEquals("Lord of the Rings", book.getTitle());
        assertNotNull(book.getAuthors());
        assertNotNull(book.getCategories());
        assertEquals(50, book.getAmount());
    }
}
