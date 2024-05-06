package com.ECI.book.library;

import static com.ECI.book.library.domain.model.DocType.CC;
import static org.junit.jupiter.api.Assertions.*;

import com.ECI.book.library.domain.model.*;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class LendTest {

    @Test
    public void testNoArgsConstructor() {
        Lend lend = new Lend();
        assertNull(lend.getId());
        assertNull(lend.getUser());
        assertNull(lend.getBook());
        assertNull(lend.getPenalty());
    }

    @Test
    public void testAllArgsConstructor() {
        User user = new User("1", "John", "Doe", 30, "12345", CC);
        Book book = new Book("2", "Some Book", null, null, 1);
        Penalty penalty = new Penalty(new Date(), 100);
        Lend lend = new Lend("1", user, book, penalty);
        assertEquals("1", lend.getId());
        assertEquals(user, lend.getUser());
        assertEquals(book, lend.getBook());
        assertEquals(penalty, lend.getPenalty());
    }

    @Test
    public void testSetters() {
        Lend lend = new Lend();
        User user = new User("2", "Jane", "Doe", 28, "67890", CC);
        Book book = new Book("3", "Another Book", null, null, 2);
        Penalty penalty = new Penalty(new Date(), 200);

        lend.setId("10");
        lend.setUser(user);
        lend.setBook(book);
        lend.setPenalty(penalty);

        assertEquals("10", lend.getId());
        assertEquals(user, lend.getUser());
        assertEquals(book, lend.getBook());
        assertEquals(penalty, lend.getPenalty());
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User("1", "John", "Doe", 30, "12345", CC);
        Book book1 = new Book("2", "Some Book", null, null, 1);
        Penalty penalty1 = new Penalty(new Date(), 100);

        Lend lend1 = new Lend("1", user1, book1, penalty1);
        Lend lend2 = new Lend("1", user1, book1, penalty1);
        Lend lend3 = new Lend("2", user1, book1, penalty1);

        assertTrue(lend1.equals(lend2));
        assertFalse(lend1.equals(lend3));
        assertEquals(lend1.hashCode(), lend2.hashCode());
        assertNotEquals(lend1.hashCode(), lend3.hashCode());
    }

    @Test
    public void testToString() {
        User user = new User("1", "John", "Doe", 30, "12345", CC);
        Book book = new Book("2", "Some Book", null, null, 1);
        Penalty penalty = new Penalty(new Date(), 100);

        Lend lend = new Lend("1", user, book, penalty);
        String expectedString = "Lend(id=1, user=User(id=1, name=John, lastName=Doe, age=30, docNumber=12345, docType=CC), book=Book(id=2, title=Some Book, authors=null, categories=null, amount=1), penalty=Penalty(id=null, dueDate=" + penalty.getDueDate() + ", amount=100))";
        assertEquals(expectedString, lend.toString());
    }
}
