package com.ECI.book.library;

import static org.junit.jupiter.api.Assertions.*;

import com.ECI.book.library.domain.model.Author;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class AuthorTest {

    @Test
    public void testNoArgsConstructor() {
        Author author = new Author();
        assertNull(author.getId());
        assertNull(author.getBiography());
        assertEquals(0, author.getAge());
        assertNull(author.getBirthday());
    }

    @Test
    public void testAllArgsConstructor() {
        Date birthday = new Date();
        Author author = new Author("1", "Some biography", 30, birthday);
        assertEquals("1", author.getId());
        assertEquals("Some biography", author.getBiography());
        assertEquals(30, author.getAge());
        assertEquals(birthday, author.getBirthday());
    }

    @Test
    public void testSettersAndGetters() {
        Author author = new Author();
        author.setId("2");
        author.setBiography("Another biography");
        author.setAge(25);
        Date newBirthday = new Date();
        author.setBirthday(newBirthday);

        assertEquals("2", author.getId());
        assertEquals("Another biography", author.getBiography());
        assertEquals(25, author.getAge());
        assertEquals(newBirthday, author.getBirthday());
    }

    @Test
    public void testEquals() {
        Author author1 = new Author("1", "Bio", 40, new Date());
        Author author2 = new Author("1", "Bio", 40, new Date());
        assertTrue(author1.equals(author2) && author2.equals(author1));
        assertEquals(author1.hashCode(), author2.hashCode());
    }

    @Test
    public void testNotEquals() {
        Author author1 = new Author("1", "Bio", 40, new Date());
        Author author2 = new Author("2", "Bio", 40, new Date());
        assertFalse(author1.equals(author2));
    }

    @Test
    public void testToString() {
        Author author = new Author("1", "Bio", 40, new Date());
        String expected = "Author(id=1, biography=Bio, age=40, birthday=" + author.getBirthday() + ")";
        assertEquals(expected, author.toString());
    }
}
