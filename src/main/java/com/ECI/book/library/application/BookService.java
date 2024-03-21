package com.ECI.book.library.application;


import com.ECI.book.library.domain.model.Book;
import com.ECI.book.library.domain.model.Lend;
import com.ECI.book.library.domain.repository.BookRepository;
import com.ECI.book.library.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Lend lendBook(String bookId, String userId) {
        return null;


    }


    public Book getBook(String bookId){
        return null;

    }
}
