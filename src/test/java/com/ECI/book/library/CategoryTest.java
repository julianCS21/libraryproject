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


    @Test
    public void testEqualsWithSelf() {
        Category category = new Category("1", "Science", "Books about science");
        assertTrue(category.equals(category), "A category should be equal to itself.");
    }

    @Test
    public void testEqualsWithDifferentIds() {
        Category category1 = new Category("1", "Science", "Detailed description");
        Category category2 = new Category("2", "Science", "Detailed description");
        assertFalse(category1.equals(category2), "Categories with different IDs should not be equal.");
    }

    @Test
    public void testEqualsWithDifferentNames() {
        Category category1 = new Category("1", "Science", "Books about science");
        Category category2 = new Category("1", "Math", "Books about science");
        assertFalse(category1.equals(category2), "Categories with different names should not be equal.");
    }

    @Test
    public void testEqualsWithDifferentDescriptions() {
        Category category1 = new Category("1", "Science", "Books about science");
        Category category2 = new Category("1", "Science", "An extensive collection of scientific books");
        assertFalse(category1.equals(category2), "Categories with different descriptions should not be equal.");
    }

    @Test
    public void testHashCodeConsistency() {
        Category category = new Category("1", "Science", "Books about science");
        int initialHashCode = category.hashCode();
        assertEquals(initialHashCode, category.hashCode(), "Hash code should be consistent.");
    }

    @Test
    public void testNullIdNameDescription() {
        Category category = new Category(null, null, null);
        assertNotNull(category.toString(), "ToString should handle null values without crashing.");
    }

    @Test
    public void testSettersWithNull() {
        Category category = new Category();
        category.setId(null);
        category.setName(null);
        category.setDescription(null);

        assertNull(category.getId(), "ID should be null after setting to null.");
        assertNull(category.getName(), "Name should be null after setting to null.");
        assertNull(category.getDescription(), "Description should be null after setting to null.");
    }

    @Test
    public void testNotEqualsDifferentValues() {
        Category category1 = new Category("1", "Science", "Books about science");
        Category category2 = new Category("1", "Science", "Different description");
        Category category3 = new Category("1", "Different name", "Books about science");
        Category category4 = new Category("2", "Science", "Books about science");

        assertFalse(category1.equals(category2), "Should not be equal with different descriptions.");
        assertFalse(category1.equals(category3), "Should not be equal with different names.");
        assertFalse(category1.equals(category4), "Should not be equal with different IDs.");
    }

    @Test
    public void testEqualityOnAllFields() {
        Category category1 = new Category("1", "Science", "Books about science");
        Category category2 = new Category("1", "Science", "Books about science");
        Category categoryDiffId = new Category("2", "Science", "Books about science");
        Category categoryDiffName = new Category("1", "Technology", "Books about science");
        Category categoryDiffDescription = new Category("1", "Science", "Scientific books");

        assertTrue(category1.equals(category2), "Should be equal when all fields are the same.");
        assertFalse(category1.equals(categoryDiffId), "Should not be equal with different IDs.");
        assertFalse(category1.equals(categoryDiffName), "Should not be equal with different names.");
        assertFalse(category1.equals(categoryDiffDescription), "Should not be equal with different descriptions.");
    }

    @Test
    public void testHashCodeWithNullValues() {
        Category category = new Category(null, null, null);
        assertDoesNotThrow(() -> category.hashCode(), "Hash code should handle null values without throwing an exception.");
    }

    @Test
    public void testToStringWithNullValues() {
        Category category = new Category(null, null, null);
        String expectedString = "Category(id=null, name=null, description=null)";
        assertEquals(expectedString, category.toString(), "ToString should correctly format string with null values.");
    }

    @Test
    public void testEqualsWithItself() {
        Category category = new Category("1", "Science", "Books about science");
        assertTrue(category.equals(category), "A category should be equal to itself.");
    }

    @Test
    public void testEqualsWithDifferentObjectTypes() {
        Category category = new Category("1", "Science", "Books about science");
        String notACategory = "Not a Category";
        assertFalse(category.equals(notACategory), "Should not be equal to an object of a different type.");
    }

    @Test
    public void testMutability() {
        Category category = new Category("1", "Science", "Books about science");
        category.setId("2");
        category.setName("Math");
        category.setDescription("Books about math");
        assertEquals("2", category.getId(), "ID should be mutable and change to '2'.");
        assertEquals("Math", category.getName(), "Name should be mutable and change to 'Math'.");
        assertEquals("Books about math", category.getDescription(), "Description should be mutable and change to 'Books about math'.");
    }
}


