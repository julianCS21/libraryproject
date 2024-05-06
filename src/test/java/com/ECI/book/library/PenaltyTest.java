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
}
