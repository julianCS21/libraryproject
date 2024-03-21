package com.ECI.book.library.domain.repository;


import com.ECI.book.library.domain.model.Book;
import com.ECI.book.library.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends MongoRepository<User,String>{
}
