package com.ECI.book.library.infraestructure.inbound;



import com.ECI.book.library.application.BookService;
import com.ECI.book.library.domain.DTO.AuthorDTO;
import com.ECI.book.library.domain.DTO.BookDTO;
import com.ECI.book.library.domain.DTO.LendDTO;
import com.ECI.book.library.domain.exceptions.LibraryException;
import com.ECI.book.library.domain.model.Author;
import com.ECI.book.library.domain.model.Book;
import com.ECI.book.library.domain.model.Lend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ECI.book.library.domain.DTO.DTOConverter.convertAuthorDTOsToAuthors;
import static com.ECI.book.library.domain.DTO.DTOConverter.convertCategoryDTOsToCategories;

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
    public ResponseEntity<Lend> lendBook(@RequestBody LendDTO lendDTO) {
        try {
            Lend lend = lendBookUseCase.lendBook(lendDTO.getBookId(), lendDTO.getUserId());
            return ResponseEntity.ok(lend);
        } catch (LibraryException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody BookDTO bookDTO){
        try {
            Book book = convertToEntity(bookDTO);
            Book savedBook = lendBookUseCase.save(book);
            return ResponseEntity.ok(savedBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthors(convertAuthorDTOsToAuthors(bookDTO.getAuthors()));
        book.setCategories(convertCategoryDTOsToCategories(bookDTO.getCategories()));
        book.setAmount(bookDTO.getAmount());
        return book;
    }




}
