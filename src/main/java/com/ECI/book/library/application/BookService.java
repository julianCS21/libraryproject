package com.ECI.book.library.application;


import com.ECI.book.library.domain.exceptions.LendsException;
import com.ECI.book.library.domain.exceptions.LibraryException;
import com.ECI.book.library.domain.exceptions.UsersException;
import com.ECI.book.library.domain.model.Book;
import com.ECI.book.library.domain.model.Lend;
import com.ECI.book.library.domain.model.User;
import com.ECI.book.library.infraestructure.repository.BookRepository;
import com.ECI.book.library.infraestructure.repository.LendRepository;
import com.ECI.book.library.infraestructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LendRepository lendRepository;

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Lend lendBook(String bookId, String userId) throws LibraryException {
        Book findBook = bookRepository.findById(bookId).orElseThrow(LendsException.BookNotFoundException::new);
        if(findBook.getAmount() == 0){
            throw new LendsException.BookNotAvailableException();
        }
        User findUser = userRepository.findById(userId).orElseThrow(UsersException.UserNotFoundException::new);
        Date today = new Date();
        List<Lend> lendsUser = lendRepository.findByUserId(userId).stream()
                .filter(lend -> lend.getPenalty() != null && lend.getPenalty().getAmount() > 0 && lend.getPenalty().getDueDate().before(today))
                .toList();

        if(!lendsUser.isEmpty()){
            throw new LendsException.PendingPenalitiesException();
        }
        Lend newLend = new Lend("1",findUser,findBook,null);
        lendRepository.save(newLend);
        findBook.setAmount(findBook.getAmount() - 1);
        bookRepository.save(findBook);
        return newLend;
    }


    public Book getBook(String bookId) throws LibraryException {
        return bookRepository.findById(bookId).orElseThrow(LendsException.BookNotFoundException::new);

    }
}
