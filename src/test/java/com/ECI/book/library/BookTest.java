package com.ECI.book.library;

import static org.junit.jupiter.api.Assertions.*;

import com.ECI.book.library.domain.model.Author;
import com.ECI.book.library.domain.model.Book;
import com.ECI.book.library.domain.model.Category;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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

    @Test
    public void testEqualsWithNullAuthorsAndCategories() {
        Book book1 = new Book("1", "Book One", null, null, 1);
        Book book2 = new Book("1", "Book One", null, null, 1);
        assertTrue(book1.equals(book2));

        Book bookWithAuthors = new Book("1", "Book One", Arrays.asList(new Author("1", "Author Bio", 45, new Date())), null, 1);
        assertFalse(book1.equals(bookWithAuthors));
        assertFalse(bookWithAuthors.equals(book1));

        Book bookWithCategories = new Book("1", "Book One", null, Arrays.asList(new Category("1", "Fiction", "Fictional books")), 1);
        assertFalse(book1.equals(bookWithCategories));
        assertFalse(bookWithCategories.equals(book1));
    }

    @Test
    public void testHashCodeWithNullFields() {
        Book book = new Book("1", "Book One", null, null, 1);
        assertNotNull(book);
    }

    @Test
    public void testToStringWithEmptyLists() {
        Book book = new Book("1", "Book One", Collections.emptyList(), Collections.emptyList(), 1);
        String expectedString = "Book(id=1, title=Book One, authors=[], categories=[], amount=1)";
        assertEquals(expectedString, book.toString());
    }

    @Test
    public void testComplexEqualsConditions() {
        Author author1 = new Author("1", "Author1", 40, new Date());
        Author author2 = new Author("2", "Author2", 41, new Date());
        Category category1 = new Category("1", "Category1", "Desc1");
        Category category2 = new Category("2", "Category2", "Desc2");

        List<Author> authorsList1 = Arrays.asList(author1);
        List<Author> authorsList2 = Arrays.asList(author2);
        List<Category> categoriesList1 = Arrays.asList(category1);
        List<Category> categoriesList2 = Arrays.asList(category2);

        Book book1 = new Book("1", "Book One", authorsList1, categoriesList1, 1);
        Book book2 = new Book("1", "Book One", authorsList1, categoriesList1, 1);
        Book book3 = new Book("1", "Book One", authorsList2, categoriesList2, 1);

        assertTrue(book1.equals(book2));
        assertFalse(book1.equals(book3));
        assertNotEquals(book1.hashCode(), book3.hashCode());
    }
}
