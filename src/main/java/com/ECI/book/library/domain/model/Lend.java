package com.ECI.book.library.domain.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "lends")
@Builder
public class Lend {

    @Id
    private String id;
    private User user;
    private Book book;
    private Penalty penalty;


}
