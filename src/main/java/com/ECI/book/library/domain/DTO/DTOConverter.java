package com.ECI.book.library.domain.DTO;

import com.ECI.book.library.domain.model.Author;
import com.ECI.book.library.domain.model.Category;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DTOConverter {

    public static List<Author> convertAuthorDTOsToAuthors(List<AuthorDTO> authorDTOs) {
        // Asegúrate de que la lista no sea null usando Optional
        return Optional.ofNullable(authorDTOs).orElse(Collections.emptyList())
                .stream()
                .map(DTOConverter::convertAuthorDTOToAuthor)
                .collect(Collectors.toList());
    }

    private static Author convertAuthorDTOToAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        return author;
    }

    public static List<Category> convertCategoryDTOsToCategories(List<CategoryDTO> categoryDTOs) {
        // Asegúrate de que la lista no sea null usando Optional
        return Optional.ofNullable(categoryDTOs).orElse(Collections.emptyList())
                .stream()
                .map(DTOConverter::convertCategoryDTOToCategory)
                .collect(Collectors.toList());
    }

    private static Category convertCategoryDTOToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }
}
