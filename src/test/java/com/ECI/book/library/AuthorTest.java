package com.ECI.book.library;

import static org.junit.jupiter.api.Assertions.*;

import com.ECI.book.library.domain.model.Author;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class AuthorTest {

    @Test
    public void testNoArgsConstructor() {
        Author author = Author.builder().build();
        assertNull(author.getId());
        assertNull(author.getBiography());
        assertEquals(0, author.getAge());
        assertNull(author.getBirthday());
    }

    @Test
    public void testAllArgsConstructor() {
        Date birthday = new Date();
        Author author = Author.builder()
                .id("1")
                .biography("Some biography")
                .age(30)
                .birthday(birthday)
                .build();
        assertEquals("1", author.getId());
        assertEquals("Some biography", author.getBiography());
        assertEquals(30, author.getAge());
        assertEquals(birthday, author.getBirthday());
    }

    @Test
    public void testSettersAndGetters() {
        Date newBirthday = new Date();
        Author author = Author.builder()
                .id("2")
                .biography("Another biography")
                .age(25)
                .birthday(newBirthday)
                .build();
        assertEquals("2", author.getId());
        assertEquals("Another biography", author.getBiography());
        assertEquals(25, author.getAge());
        assertEquals(newBirthday, author.getBirthday());
    }

    @Test
    public void testEquals() {
        Date birthDate = new Date();
        Author author1 = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(birthDate)
                .build();
        Author author2 = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(birthDate)
                .build();
        assertTrue(author1.equals(author2) && author2.equals(author1));
        assertEquals(author1.hashCode(), author2.hashCode());
    }

    @Test
    public void testNotEquals() {
        Date birthDate = new Date();
        Author author1 = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(birthDate)
                .build();
        Author author2 = Author.builder()
                .id("2")
                .biography("Bio")
                .age(40)
                .birthday(birthDate)
                .build();
        assertFalse(author1.equals(author2));
    }

    @Test
    public void testToString() {
        Date birthday = new Date();
        Author author = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(birthday)
                .build();
        String expected = "Author(id=1, biography=Bio, name=null, age=40, birthday=" + author.getBirthday() + ")";
        assertEquals(expected, author.toString());
    }

    @Test
    public void testBuilder() {
        Date birthday = new Date();
        Author author = Author.builder()
                .id("3")
                .biography("Builder biography")
                .age(50)
                .birthday(birthday)
                .build();

        assertNotNull(author);
        assertEquals("3", author.getId());
        assertEquals("Builder biography", author.getBiography());
        assertEquals(50, author.getAge());
        assertEquals(birthday, author.getBirthday());
    }


    @Test
    public void testEqualsWithDifferentId() {
        Date birthday = new Date();
        Author author1 = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(birthday)
                .build();
        Author author2 = Author.builder()
                .id("2")
                .biography("Bio")
                .age(40)
                .birthday(birthday)
                .build();
        assertFalse(author1.equals(author2));
    }

    @Test
    public void testEqualsWithDifferentBiography() {
        Date birthday = new Date();
        Author author1 = Author.builder()
                .id("1")
                .biography("Bio1")
                .age(40)
                .birthday(birthday)
                .build();
        Author author2 = Author.builder()
                .id("1")
                .biography("Bio2")
                .age(40)
                .birthday(birthday)
                .build();
        assertFalse(author1.equals(author2));
    }

    @Test
    public void testEqualsWithDifferentAge() {
        Date birthday = new Date();
        Author author1 = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(birthday)
                .build();
        Author author2 = Author.builder()
                .id("1")
                .biography("Bio")
                .age(41)
                .birthday(birthday)
                .build();
        assertFalse(author1.equals(author2));
    }

    @Test
    public void testEqualsWithDifferentBirthday() {
        Author author1 = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(new Date(1000000000000L))
                .build();
        Author author2 = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(new Date(1000000000001L))
                .build();
        assertFalse(author1.equals(author2));
    }

    @Test
    public void testEqualsWithNull() {
        Date birthday = new Date();
        Author author = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(birthday)
                .build();
        assertFalse(author.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Date birthday = new Date();
        Author author = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(birthday)
                .build();
        assertFalse(author.equals(new String("Different Class")));
    }

    @Test
    public void testHashCodeConsistency() {
        Date birthday = new Date();
        Author author = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(birthday)
                .build();
        int initialHashCode = author.hashCode();
        assertEquals(initialHashCode, author.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentValues() {
        Date birthday = new Date();
        Author author1 = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(birthday)
                .build();
        Author author2 = Author.builder()
                .id("1")
                .biography("Bio")
                .age(41)
                .birthday(birthday)
                .build();
        assertNotEquals(author1.hashCode(), author2.hashCode());
    }

    @Test
    public void testEqualsWithNullFields() {
        Author author1 = Author.builder()
                .id("1")
                .biography(null)
                .age(40)
                .birthday(null)
                .build();
        Author author2 = Author.builder()
                .id("1")
                .biography(null)
                .age(40)
                .birthday(null)
                .build();
        Author author3 = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(new Date())
                .build();
        assertTrue(author1.equals(author2));
        assertFalse(author1.equals(author3));
    }

    @Test
    public void testNullBiographyAndBirthdayInEqualsAndHashcode() {
        Author authorWithNullFields = Author.builder()
                .id("1")
                .biography(null)
                .age(40)
                .birthday(null)
                .build();
        Date birthday = new Date();
        Author authorWithNonNullFields = Author.builder()
                .id("1")
                .biography("Bio")
                .age(40)
                .birthday(birthday)
                .build();
        assertFalse(authorWithNullFields.equals(authorWithNonNullFields));
        assertNotEquals(authorWithNullFields.hashCode(), authorWithNonNullFields.hashCode());
    }


}
