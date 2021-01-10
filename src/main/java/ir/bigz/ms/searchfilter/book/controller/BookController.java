package ir.bigz.ms.searchfilter.book.controller;

import ir.bigz.ms.searchfilter.book.filter.BookFilterRequest;
import ir.bigz.ms.searchfilter.book.model.Book;
import ir.bigz.ms.searchfilter.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@Slf4j
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{bookid}")
    public ResponseEntity<?> getBook(@PathVariable("bookid") long bookId){
        Book book = bookService.getBook(bookId);
        log.info("book: " + book.toString());
        return new ResponseEntity<Book>(book, HttpStatus.ACCEPTED);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getBookByFilter(
            @RequestParam int page,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String sbn){
        BookFilterRequest bookFilterRequest = new BookFilterRequest();
        bookFilterRequest.setTitle(title);
        bookFilterRequest.setSbn(sbn);
        Page<Book> books = bookService.getByFilter(page, bookFilterRequest);
        return new ResponseEntity<>(books, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Book book){
        bookService.addBook(book);
        return new ResponseEntity("add success", HttpStatus.ACCEPTED);
    }
}

