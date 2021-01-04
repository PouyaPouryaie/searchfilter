package ir.bigz.ms.searchfilter.controller;

import ir.bigz.ms.searchfilter.filter.BookFilterRequest;
import ir.bigz.ms.searchfilter.model.Book;
import ir.bigz.ms.searchfilter.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/{bookid}")
    public ResponseEntity<?> getBook(@PathVariable("bookid") long bookId){
        Book book = bookService.getBook(bookId);
        log.info("book: " + book.toString());
        return new ResponseEntity<Book>(book, HttpStatus.ACCEPTED);
    }

    @GetMapping("/book/filter")
    public ResponseEntity<?> getBookByFilter(
            @RequestParam int page,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author){
        BookFilterRequest bookFilterRequest = new BookFilterRequest();
        bookFilterRequest.setTitle(title);
        bookFilterRequest.setAuthor(author);
        Page<Book> books = bookService.getByFilter(page, bookFilterRequest);
        return new ResponseEntity<>(books, HttpStatus.ACCEPTED);
    }

    @PostMapping("/book")
    public ResponseEntity<?> addBook(@RequestBody Book book){
        bookService.addBook(book);
        return new ResponseEntity("add success", HttpStatus.ACCEPTED);
    }
}

