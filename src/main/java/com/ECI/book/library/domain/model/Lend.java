package com.ECI.book.library.domain.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


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

    public Lend(Penalty penalty,Book book, User user){
        this.penalty = penalty;
        this.book = book;
        this.user = user;
    }


}
