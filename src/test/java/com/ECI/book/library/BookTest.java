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
        List<Author> authors = Arrays.asList(Author.builder().id("1").biography("Author Bio").age(45).birthday(null).build());
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

        Book bookWithAuthors = new Book("1", "Book One", Arrays.asList(Author.builder().id("1").biography("Author1").age(40).birthday(new Date()).build()), null, 1);
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
        Author author1 = Author.builder().id("1").biography("Author1").age(40).birthday(new Date()).build();
        Author author2 = Author.builder().id("2").biography("Author2").age(41).birthday(new Date()).build();

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



    @Test
    public void testSetters() {
        Book book = new Book();
        Author author = Author.builder().id("1").biography("Author Name").age(50).birthday(new Date()).build();
        Category category = new Category("1", "Category Name", "Description");

        book.setId("3");
        book.setTitle("New Title");
        book.setAuthors(Arrays.asList(author));
        book.setCategories(Arrays.asList(category));
        book.setAmount(25);

        assertEquals("3", book.getId());
        assertEquals("New Title", book.getTitle());
        assertEquals(1, book.getAuthors().size());
        assertEquals(1, book.getCategories().size());
        assertEquals(25, book.getAmount());
    }

    @Test
    public void testEqualsWithDifferentObjects() {
        Book book1 = new Book("1", "Book One", null, null, 1);
        Author author = Author.builder().id("1").biography("Author1").age(40).birthday(new Date()).build();
        List<Author> authors = Arrays.asList(author);
        Book book2 = new Book("1", "Book One", authors, Arrays.asList(new Category("1", "Category", "Desc")), 1);
        assertFalse(book1.equals(book2)); // Testing equality with different authors and categories
    }

    @Test
    public void testHashCodeConsistencyAndDifferences() {
        Book book1 = new Book("1", "Book One", null, null, 1);
        Book book2 = new Book("1", "Book One", null, null, 1);
        Book book3 = new Book("2", "Book Two", null, null, 2);

        assertEquals(book1.hashCode(), book2.hashCode()); // Hash codes should be the same for equal objects
        assertNotEquals(book1.hashCode(), book3.hashCode()); // Hash codes should be different for non-equal objects
    }

    @Test
    public void testToStringVariations() {
        Book book = new Book("1", "Book One", Arrays.asList(Author.builder().id("1").biography("Author").age(40).birthday(new Date()).build()), Arrays.asList(new Category("1", "Category", "Desc")), 1);
        String expected = "Book(id=1, title=Book One, authors=[Author(id=1, biography=Author, name=null, age=40, birthday=" + book.getAuthors().get(0).getBirthday() + ")], categories=[Category(id=1, name=Category, description=Desc)], amount=1)";
        assertEquals(expected, book.toString());
    }


    @Test
    public void testSettersAndEmptyConstructor() {
        Book book = new Book();
        book.setId("10");
        book.setTitle("Empty Test");
        book.setAuthors(Collections.emptyList());
        book.setCategories(Collections.emptyList());
        book.setAmount(0);

        assertEquals("10", book.getId());
        assertEquals("Empty Test", book.getTitle());
        assertTrue(book.getAuthors().isEmpty());
        assertTrue(book.getCategories().isEmpty());
        assertEquals(0, book.getAmount());
    }

    @Test
    public void testEqualsWithSameReferences() {
        List<Author> authors = Arrays.asList(Author.builder().id("1").biography("Author1").age(40).birthday(new Date()).build());
        List<Category> categories = Arrays.asList(new Category("1", "Category One", "A category"));
        Book book1 = new Book("1", "Book One", authors, categories, 5);
        Book book2 = book1;

        assertTrue(book1.equals(book2));
    }

    @Test
    public void testEqualsWithNullsInList() {
        Book book1 = new Book("1", "Book One", null, null, 1);
        Book book2 = new Book("1", "Book One", null, null, 1);

        assertTrue(book1.equals(book2));

        List<Author> authors = null;
        List<Category> categories = null;
        book1.setAuthors(authors);
        book1.setCategories(categories);

        assertTrue(book1.equals(book2));
    }

    @Test
    public void testNotEqualDifferentLists() {
        List<Author> authors1 = Arrays.asList(Author.builder().id("1").biography("Author1").age(40).birthday(new Date()).build());
        List<Author> authors2 = Arrays.asList(Author.builder().id("2").biography("Author2").age(50).birthday(new Date()).build());
        List<Category> categories1 = Arrays.asList(new Category("1", "Category One", "Description One"));
        List<Category> categories2 = Arrays.asList(new Category("2", "Category Two", "Description Two"));

        Book book1 = new Book("1", "Book One", authors1, categories1, 1);
        Book book2 = new Book("1", "Book One", authors2, categories2, 1);

        assertFalse(book1.equals(book2));
    }

    @Test
    public void testHashCodeVariability() {
        Book book1 = new Book("1", "Book One", Arrays.asList(Author.builder().id("1").biography("Author1").age(40).birthday(new Date()).build()), Arrays.asList(new Category("1", "Category", "Desc")), 1);
        Book book2 = new Book("1", "Book One", Arrays.asList(Author.builder().id("1").biography("Author1").age(40).birthday(new Date()).build()), Arrays.asList(new Category("1", "Category", "Desc")), 1);
        Book book3 = new Book("2", "Book Two", Arrays.asList(Author.builder().id("2").biography("Author2").age(50).birthday(new Date()).build()), Arrays.asList(new Category("2", "Category", "Desc")), 2);

        assertEquals(book1.hashCode(), book2.hashCode());
        assertNotEquals(book1.hashCode(), book3.hashCode());
    }

    @Test
    public void testToStringComplex() {
        Author author = Author.builder().id("1").biography("Complex Author").age(50).birthday(new Date()).build();
        Category category = new Category("1", "Complex Category", "Complex Description");
        Book book = new Book("1", "Complex Book", Arrays.asList(author), Arrays.asList(category), 15);

        String expectedString = "Book(id=1, title=Complex Book, authors=[Author(id=1, biography=Complex Author, name=null, age=50, birthday=" + author.getBirthday() + ")], categories=[Category(id=1, name=Complex Category, description=Complex Description)], amount=15)";
        assertEquals(expectedString, book.toString());
    }
}
