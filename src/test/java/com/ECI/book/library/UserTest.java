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

    @Test
    public void testSetters() {
        User user = new User();
        user.setId("3");
        user.setName("Bob");
        user.setLastName("Brown");
        user.setAge(45);
        user.setDocNumber("65432");
        user.setDocType(DocType.CC);

        assertEquals("3", user.getId());
        assertEquals("Bob", user.getName());
        assertEquals("Brown", user.getLastName());
        assertEquals(45, user.getAge());
        assertEquals("65432", user.getDocNumber());
        assertEquals(DocType.CC, user.getDocType());
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User("1", "Jane", "Doe", 28, "98765", CC);
        User user2 = new User("1", "Jane", "Doe", 28, "98765", CC);
        User user3 = new User("2", "Alice", "Smith", 32, "54321", CC);

        assertTrue(user1.equals(user2));
        assertFalse(user1.equals(user3));
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

    @Test
    public void testToString() {
        User user = new User("4", "Charlie", "Brown", 40, "11111", DocType.CC);
        String expectedString = "User(id=4, name=Charlie, lastName=Brown, age=40, docNumber=11111, docType=CC)";
        assertEquals(expectedString, user.toString());
    }
}
