package com.ECI.book.library;

import static com.ECI.book.library.domain.model.DocType.CC;
import static org.junit.jupiter.api.Assertions.*;

import com.ECI.book.library.domain.model.DocType;
import com.ECI.book.library.domain.model.User;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void testNoArgsConstructor() {
        User user = new User();
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getLastName());
        assertEquals(0, user.getAge());
        assertNull(user.getDocNumber());
        assertNull(user.getDocType());
    }

    @Test
    public void testAllArgsConstructor() {

        User user = new User("1", "Jane", "Doe", 28, "98765", CC);
        assertEquals("1", user.getId());
        assertEquals("Jane", user.getName());
        assertEquals("Doe", user.getLastName());
        assertEquals(28, user.getAge());
        assertEquals("98765", user.getDocNumber());
        assertEquals(CC, user.getDocType());
    }

    @Test
    public void testBuilder() {

        User user = User.builder()
                .id("2")
                .name("Alice")
                .lastName("Smith")
                .age(32)
                .docNumber("54321")
                .docType(CC)
                .build();
        assertEquals("2", user.getId());
        assertEquals("Alice", user.getName());
        assertEquals("Smith", user.getLastName());
        assertEquals(32, user.getAge());
        assertEquals("54321", user.getDocNumber());
        assertEquals(CC, user.getDocType());
    }
}
