package com.ECI.book.library.infraestructure.inbound;



import com.ECI.book.library.application.BookService;
import com.ECI.book.library.domain.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService lendBookUseCase;

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAll(){
        return ResponseEntity.ok(lendBookUseCase.getAll());
    }






}
