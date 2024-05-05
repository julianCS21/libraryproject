package com.ECI.book.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;

import com.ECI.book.library.domain.model.Author;
import org.junit.jupiter.api.Test;

public class AuthorTest {

    @Test
    public void testNoArgsConstructor() {
        Author author = new Author();
        assertEquals(null, author.getId());
        assertEquals(null, author.getBiography());
        assertEquals(0, author.getAge());
        assertEquals(null, author.getBirthday());
    }

    @Test
    public void testAllArgsConstructor() {
        Date date = new Date();
        Author author = new Author("1", "biography", 30, date);
        assertEquals("1", author.getId());
        assertEquals("biography", author.getBiography());
        assertEquals(30, author.getAge());
        assertEquals(date, author.getBirthday());
    }

    @Test
    public void testBuilder() {
        Date date = new Date();
        Author author = Author.builder()
                .id("1")
                .biography("biography")
                .age(30)
                .birthday(date)
                .build();

        assertEquals("1", author.getId());
        assertEquals("biography", author.getBiography());
        assertEquals(30, author.getAge());
        assertEquals(date, author.getBirthday());
    }

    @Test
    public void testSettersAndGetters() {
        Author author = new Author();
        author.setId("2");
        author.setBiography("new bio");
        author.setAge(25);
        Date newDate = new Date();
        author.setBirthday(newDate);

        assertEquals("2", author.getId());
        assertEquals("new bio", author.getBiography());
        assertEquals(25, author.getAge());
        assertEquals(newDate, author.getBirthday());
    }
}
