package com.ECI.book.library;

import static org.junit.jupiter.api.Assertions.*;

import com.ECI.book.library.domain.model.Category;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void testNoArgsConstructor() {
        Category category = new Category();
        assertNull(category.getId());
        assertNull(category.getName());
        assertNull(category.getDescription());
    }

    @Test
    public void testAllArgsConstructor() {
        Category category = new Category("1", "Science", "Books about science");
        assertEquals("1", category.getId());
        assertEquals("Science", category.getName());
        assertEquals("Books about science", category.getDescription());
    }

    @Test
    public void testBuilder() {
        Category category = Category.builder()
                .id("2")
                .name("History")
                .description("Books about history")
                .build();
        assertEquals("2", category.getId());
        assertEquals("History", category.getName());
        assertEquals("Books about history", category.getDescription());
    }

    @Test
    public void testSetters() {
        Category category = new Category();
        category.setId("3");
        category.setName("Mathematics");
        category.setDescription("Books about mathematics");

        assertEquals("3", category.getId());
        assertEquals("Mathematics", category.getName());
        assertEquals("Books about mathematics", category.getDescription());
    }

    @Test
    public void testEqualsAndHashCode() {
        Category category1 = new Category("1", "Science", "Books about science");
        Category category2 = new Category("1", "Science", "Books about science");
        Category category3 = new Category("2", "Math", "Books about math");

        assertTrue(category1.equals(category2));
        assertFalse(category1.equals(category3));
        assertEquals(category1.hashCode(), category2.hashCode());
        assertNotEquals(category1.hashCode(), category3.hashCode());
    }

    @Test
    public void testToString() {
        Category category = new Category("4", "Technology", "Books about technology");
        String expectedString = "Category(id=4, name=Technology, description=Books about technology)";
        assertEquals(expectedString, category.toString());
    }

    @Test
    public void testEqualsWithNull() {
        Category category = new Category("1", "Science", "Books about science");
        assertFalse(category.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Category category = new Category("1", "Science", "Books about science");
        assertFalse(category.equals(new String("Different Class")));
    }
}


