package com.ECI.book.library;

import static org.junit.jupiter.api.Assertions.*;

import com.ECI.book.library.domain.model.Penalty;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class PenaltyTest {
    @Test
    public void testNoArgsConstructor() {
        Penalty penalty = new Penalty();
        assertNull(penalty.getId());
        assertNull(penalty.getDueDate());
        assertEquals(0, penalty.getAmount());
    }

    @Test
    public void testAllArgsConstructor() {
        Date dueDate = new Date();
        Penalty penalty = new Penalty("1", dueDate, 200);
        assertEquals("1", penalty.getId());
        assertEquals(dueDate, penalty.getDueDate());
        assertEquals(200, penalty.getAmount());
    }

    @Test
    public void testBuilder() {
        Date dueDate = new Date();
        Penalty penalty = Penalty.builder()
                .id("2")
                .dueDate(dueDate)
                .amount(300)
                .build();
        assertEquals("2", penalty.getId());
        assertEquals(dueDate, penalty.getDueDate());
        assertEquals(300, penalty.getAmount());
    }

    @Test
    public void testSetters() {
        Penalty penalty = new Penalty();
        Date newDueDate = new Date();
        penalty.setId("3");
        penalty.setDueDate(newDueDate);
        penalty.setAmount(400);

        assertEquals("3", penalty.getId());
        assertEquals(newDueDate, penalty.getDueDate());
        assertEquals(400, penalty.getAmount());
    }

    @Test
    public void testEqualsAndHashCode() {
        Date date = new Date();
        Penalty penalty1 = new Penalty("1", date, 100);
        Penalty penalty2 = new Penalty("1", date, 100);
        Penalty penalty3 = new Penalty("2", date, 200);

        assertTrue(penalty1.equals(penalty2));
        assertFalse(penalty1.equals(penalty3));
        assertEquals(penalty1.hashCode(), penalty2.hashCode());
        assertNotEquals(penalty1.hashCode(), penalty3.hashCode());
    }

    @Test
    public void testToString() {
        Date dueDate = new Date();
        Penalty penalty = new Penalty("4", dueDate, 500);
        String expectedString = "Penalty(id=4, dueDate=" + dueDate + ", amount=500)";
        assertEquals(expectedString, penalty.toString());
    }
}
