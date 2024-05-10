package com.ECI.book.library.domain.DTO;

import com.ECI.book.library.domain.DTO.AuthorDTO;
import com.ECI.book.library.domain.DTO.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    private String id;
    private String title;
    private List<AuthorDTO> authors;
    private List<CategoryDTO> categories;
    private int amount;
}
