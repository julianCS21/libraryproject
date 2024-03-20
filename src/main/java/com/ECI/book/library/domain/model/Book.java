package com.ECI.book.library.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Book {

    @Id
    private String id;


}
