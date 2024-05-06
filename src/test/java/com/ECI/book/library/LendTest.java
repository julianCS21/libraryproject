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

    @Test
    public void testEqualsWithDifferentUser() {
        User user1 = new User("1", "John", "Doe", 30, "12345", CC);
        User user2 = new User("2", "Jane", "Doe", 28, "67890", CC);
        Book book = new Book("2", "Some Book", null, null, 1);
        Penalty penalty = new Penalty(new Date(), 100);
        Lend lend1 = new Lend("1", user1, book, penalty);
        Lend lend2 = new Lend("1", user2, book, penalty);

        assertFalse(lend1.equals(lend2), "Lends should not be equal with different users.");
    }

    @Test
    public void testEqualsWithDifferentBook() {
        User user = new User("1", "John", "Doe", 30, "12345", CC);
        Book book1 = new Book("2", "Some Book", null, null, 1);
        Book book2 = new Book("3", "Another Book", null, null, 2);
        Penalty penalty = new Penalty(new Date(), 100);
        Lend lend1 = new Lend("1", user, book1, penalty);
        Lend lend2 = new Lend("1", user, book2, penalty);

        assertFalse(lend1.equals(lend2), "Lends should not be equal with different books.");
    }

    @Test
    public void testEqualsWithDifferentPenalty() {
        User user = new User("1", "John", "Doe", 30, "12345", CC);
        Book book = new Book("2", "Some Book", null, null, 1);
        Penalty penalty1 = new Penalty(new Date(), 100);
        Penalty penalty2 = new Penalty(new Date(), 200);
        Lend lend1 = new Lend("1", user, book, penalty1);
        Lend lend2 = new Lend("1", user, book, penalty2);

        assertFalse(lend1.equals(lend2), "Lends should not be equal with different penalties.");
    }

    @Test
    public void testNullFieldsInEqualsAndHashCode() {
        User user = new User("1", "John", "Doe", 30, "12345", CC);
        Book book = new Book("2", "Some Book", null, null, 1);
        Lend lend1 = new Lend("1", user, book, null);
        Lend lend2 = new Lend("1", user, book, null);

        assertTrue(lend1.equals(lend2), "Lends with null penalties should be equal.");
        assertEquals(lend1.hashCode(), lend2.hashCode(), "Hash codes should match for lends with null penalties.");
    }

    @Test
    public void testSettersWithNull() {
        Lend lend = new Lend();
        lend.setUser(null);
        lend.setBook(null);
        lend.setPenalty(null);

        assertNull(lend.getUser(), "User should be null after setting to null.");
        assertNull(lend.getBook(), "Book should be null after setting to null.");
        assertNull(lend.getPenalty(), "Penalty should be null after setting to null.");
    }

    @Test
    public void testToStringWithNullFields() {
        Lend lend = new Lend("1", null, null, null);
        String expectedString = "Lend(id=1, user=null, book=null, penalty=null)";
        assertEquals(expectedString, lend.toString(), "ToString should correctly format string with null fields.");
    }

    @Test
    public void testBuilderCompleteness() {
        Date today = new Date();
        User user = new User("1", "Alice", "Johnson", 28, "12345678", CC);
        Book book = new Book("1", "Effective Java", null, null, 3);
        Penalty penalty = new Penalty(today, 150);

        Lend lend = Lend.builder()
                .id("1")
                .user(user)
                .book(book)
                .penalty(penalty)
                .build();

        assertEquals("1", lend.getId());
        assertEquals(user, lend.getUser());
        assertEquals(book, lend.getBook());
        assertEquals(penalty, lend.getPenalty());
    }


    @Test
    public void testHashCodeNullSafety() {
        Lend lend = new Lend(null, null, null, null);
        assertDoesNotThrow(lend::hashCode, "HashCode should handle null values gracefully.");
    }

    @Test
    public void testEqualsReflectivity() {
        Date today = new Date();
        User user = new User("1", "Alice", "Johnson", 28, "12345678", CC);
        Book book = new Book("1", "Effective Java", null, null, 3);
        Penalty penalty = new Penalty(today, 150);

        Lend lend1 = new Lend("1", user, book, penalty);
        Lend lend2 = new Lend("1", user, book, penalty);

        assertTrue(lend1.equals(lend2) && lend2.equals(lend1), "Equals should be reflective and symmetric.");
    }

    @Test
    public void testEqualsConsistency() {
        User user1 = new User("1", "Alice", "Johnson", 28, "12345678", CC);
        User user2 = new User("1", "Alice", "Johnson", 28, "12345678", CC);
        Book book1 = new Book("1", "Effective Java", null, null, 3);
        Book book2 = new Book("1", "Effective Java", null, null, 3);
        Penalty penalty1 = new Penalty(new Date(), 150);
        Penalty penalty2 = new Penalty(new Date(), 150);

        Lend lend1 = new Lend("1", user1, book1, penalty1);
        Lend lend2 = new Lend("1", user2, book2, penalty2);

        assertTrue(lend1.equals(lend2), "Equals should be consistent across multiple calls.");
    }

    @Test
    public void testToStringNullValues() {
        Lend lend = new Lend("1", null, null, null);
        String expected = "Lend(id=1, user=null, book=null, penalty=null)";
        assertEquals(expected, lend.toString(), "ToString should handle null fields correctly.");
    }


    @Test
    public void testLendEqualityWithDifferentPenaltyValues() {
        User user = new User("1", "John", "Doe", 30, "12345", CC);
        Book book = new Book("2", "Some Book", null, null, 1);
        Penalty penalty1 = new Penalty(new Date(), 100);
        Penalty penalty2 = new Penalty(new Date(), 200);
        Lend lend1 = new Lend("1", user, book, penalty1);
        Lend lend2 = new Lend("1", user, book, penalty2);

        assertFalse(lend1.equals(lend2), "Lends should not be equal if the penalties are different.");
    }

    @Test
    public void testLendNullUserAndBookEquality() {
        Penalty penalty = new Penalty(new Date(), 100);
        Lend lend1 = new Lend("1", null, null, penalty);
        Lend lend2 = new Lend("1", null, null, penalty);

        assertTrue(lend1.equals(lend2), "Lends should be equal if both user and book are null and other fields match.");
    }

    @Test
    public void testHashCodeWithAllFieldsNull() {
        Lend lend = new Lend(null, null, null, null);
        assertDoesNotThrow(() -> lend.hashCode(), "Hash code generation should handle all null values without throwing.");
    }

    @Test
    public void testToStringComplex() {
        User user = new User("1", "Alice", "Johnson", 28, "12345678", CC);
        Book book = new Book("1", "Effective Java", null, null, 3);
        Penalty penalty = new Penalty(new Date(), 150);
        Lend lend = new Lend("complexId", user, book, penalty);

        String expected = "Lend(id=complexId, user=User(id=1, name=Alice, lastName=Johnson, age=28, docNumber=12345678, docType=CC), book=Book(id=1, title=Effective Java, authors=null, categories=null, amount=3), penalty=Penalty(id=null, dueDate=" + penalty.getDueDate() + ", amount=150))";
        assertEquals(expected, lend.toString(), "ToString should generate a comprehensive description of the Lend object.");
    }

    @Test
    public void testLendInequalityWithDifferentIDs() {
        User user = new User("1", "John", "Doe", 30, "12345", CC);
        Book book = new Book("2", "Some Book", null, null, 1);
        Penalty penalty = new Penalty(new Date(), 100);
        Lend lend1 = new Lend("1", user, book, penalty);
        Lend lend2 = new Lend("2", user, book, penalty);

        assertFalse(lend1.equals(lend2), "Lends with different IDs should not be equal.");
    }

    @Test
    public void testPenaltyDateEdgeCases() {
        User user = new User("1", "John", "Doe", 30, "12345", CC);
        Book book = new Book("2", "Some Book", null, null, 1);
        Date now = new Date();
        Penalty penaltyJustBeforeNow = new Penalty(new Date(now.getTime() - 1), 100);
        Penalty penaltyJustAfterNow = new Penalty(new Date(now.getTime() + 1), 100);
        Lend lend1 = new Lend("1", user, book, penaltyJustBeforeNow);
        Lend lend2 = new Lend("1", user, book, penaltyJustAfterNow);

        assertFalse(lend1.equals(lend2), "Lends should not be equal if the penalties' dates are on edge cases.");
    }

    @Test
    public void testSettersAndGetters() {
        User user = new User("1", "John", "Doe", 30, "12345", CC);
        Book book = new Book("2", "Some Book", null, null, 1);
        Penalty penalty = new Penalty(new Date(), 100);
        Lend lend = new Lend();

        lend.setId("5");
        lend.setUser(user);
        lend.setBook(book);
        lend.setPenalty(penalty);

        assertEquals("5", lend.getId());
        assertSame(user, lend.getUser());
        assertSame(book, lend.getBook());
        assertSame(penalty, lend.getPenalty());
    }

    @Test
    public void testEqualityAndHashcodeWithNulls() {
        Lend lend1 = new Lend(null, null, null, null);
        Lend lend2 = new Lend(null, null, null, null);

        assertTrue(lend1.equals(lend2));
        assertEquals(lend1.hashCode(), lend2.hashCode());
    }

    @Test
    public void testInequalityDueToDifferentClasses() {
        Lend lend = new Lend("1", new User(), new Book(), new Penalty(new Date(), 100));
        Object other = new Object();

        assertFalse(lend.equals(other));
    }

    @Test
    public void testPenaltyEqualsWithDifferentDates() {
        Date date1 = new Date();
        Date date2 = new Date(date1.getTime() + 1000); // one second later
        Penalty penalty1 = new Penalty(date1, 100);
        Penalty penalty2 = new Penalty(date2, 100);

        assertFalse(penalty1.equals(penalty2));
    }

    @Test
    public void testUserIntegrationInLend() {
        User user = new User("1", "John", "Doe", 30, "12345", CC);
        Book book = new Book("2", "Book Title", null, null, 10);
        Penalty penalty = new Penalty(new Date(), 50);
        Lend lend = new Lend("10", user, book, penalty);

        assertEquals("John", lend.getUser().getName());
        assertEquals("Book Title", lend.getBook().getTitle());
        assertEquals(50, lend.getPenalty().getAmount());
    }

    @Test
    public void testLendToStringDetailed() {
        User user = new User("1", "Alice", "Wonderland", 28, "67890", CC);
        Book book = new Book("3", "Curiouser and Curiouser", null, null, 5);
        Penalty penalty = new Penalty(new Date(), 150);
        Lend lend = new Lend("15", user, book, penalty);

        String expected = "Lend(id=15, user=User(id=1, name=Alice, lastName=Wonderland, age=28, docNumber=67890, docType=CC), book=Book(id=3, title=Curiouser and Curiouser, authors=null, categories=null, amount=5), penalty=Penalty(id=null, dueDate=" + penalty.getDueDate() + ", amount=150))";
        assertEquals(expected, lend.toString());
    }


}
