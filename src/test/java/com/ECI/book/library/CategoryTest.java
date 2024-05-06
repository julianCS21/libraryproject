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
}
