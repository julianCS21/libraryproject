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
        User user = new User("1", "John", "Doe", 30, "12345",CC);
        Book book = new Book("2", "Some Book", null, null, 1);
        Penalty penalty = new Penalty(new Date(), 100);
        Lend lend = new Lend("1", user, book, penalty);
        assertEquals("1", lend.getId());
        assertEquals(user, lend.getUser());
        assertEquals(book, lend.getBook());
        assertEquals(penalty, lend.getPenalty());
    }
}
