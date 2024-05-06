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

    @Test
    public void testUserEqualityWithNullValues() {
        User user1 = new User(null, null, null, 0, null, null);
        User user2 = new User(null, null, null, 0, null, null);

        assertTrue(user1.equals(user2), "Two users with null values should be considered equal.");
        assertEquals(user1.hashCode(), user2.hashCode(), "Hash codes should match for users with null values.");
    }

    @Test
    public void testUserInequalityWithDifferentAges() {
        User user1 = new User("1", "Jane", "Doe", 28, "98765", CC);
        User user2 = new User("1", "Jane", "Doe", 30, "98765", CC); // different age

        assertFalse(user1.equals(user2), "Users should not be equal if they have different ages.");
    }

    @Test
    public void testUserInequalityWithDifferentDocumentNumbers() {
        User user1 = new User("1", "Jane", "Doe", 28, "98765", CC);
        User user2 = new User("1", "Jane", "Doe", 28, "12345", CC); // different document number

        assertFalse(user1.equals(user2), "Users should not be equal if they have different document numbers.");
    }

    @Test
    public void testToStringHandlesNullValues() {
        User user = new User(null, null, null, 0, null, null);
        String expectedString = "User(id=null, name=null, lastName=null, age=0, docNumber=null, docType=null)";
        assertEquals(expectedString, user.toString(), "ToString should handle null values gracefully.");
    }

    @Test
    public void testSetNullValues() {
        User user = new User();
        user.setId(null);
        user.setName(null);
        user.setLastName(null);
        user.setAge(0);
        user.setDocNumber(null);
        user.setDocType(null);

        assertNull(user.getId(), "ID should be null after being set to null.");
        assertNull(user.getName(), "Name should be null after being set to null.");
        assertNull(user.getLastName(), "Last name should be null after being set to null.");
        assertEquals(0, user.getAge(), "Age should be zero after being set to zero.");
        assertNull(user.getDocNumber(), "Document number should be null after being set to null.");
        assertNull(user.getDocType(), "Document type should be null after being set to null.");
    }

    @Test
    public void testAllFieldsMutability() {
        User user = new User("1", "Jane", "Doe", 28, "98765", CC);
        user.setId("2");
        user.setName("Alice");
        user.setLastName("Smith");
        user.setAge(30);
        user.setDocNumber("54321");
        user.setDocType(DocType.CC);

        assertEquals("2", user.getId());
        assertEquals("Alice", user.getName());
        assertEquals("Smith", user.getLastName());
        assertEquals(30, user.getAge());
        assertEquals("54321", user.getDocNumber());
        assertEquals(DocType.CC, user.getDocType());
    }
}
