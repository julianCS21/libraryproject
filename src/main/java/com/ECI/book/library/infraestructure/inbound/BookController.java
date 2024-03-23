package com.ECI.book.library.infraestructure.inbound;



import com.ECI.book.library.application.BookService;
import com.ECI.book.library.domain.exceptions.LibraryException;
import com.ECI.book.library.domain.model.Book;
import com.ECI.book.library.domain.model.Lend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/lend")
    public ResponseEntity<Lend> lendBook(@RequestParam("bookId") String bookId, @RequestParam("userId") String userId) {
        try {
            Lend lend = lendBookUseCase.lendBook(bookId, userId);
            return ResponseEntity.ok(lend);
        } catch (LibraryException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }









}
