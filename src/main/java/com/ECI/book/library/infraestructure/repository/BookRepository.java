package com.ECI.book.library.infraestructure.repository;

import com.ECI.book.library.domain.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {

}
