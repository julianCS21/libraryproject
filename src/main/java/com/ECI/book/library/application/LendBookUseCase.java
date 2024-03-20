package com.ECI.book.library.application;


import com.ECI.book.library.domain.model.Book;
import com.ECI.book.library.domain.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LendBookUseCase {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

}
