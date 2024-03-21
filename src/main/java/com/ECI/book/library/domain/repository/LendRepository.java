package com.ECI.book.library.domain.repository;

import com.ECI.book.library.domain.model.Lend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LendRepository extends MongoRepository<Lend,String> {

    List<Lend> findByUserId(String id);

}
