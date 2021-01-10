package ir.bigz.ms.searchfilter.author.controller;

import ir.bigz.ms.searchfilter.author.filter.AuthorFilterRequest;
import ir.bigz.ms.searchfilter.author.model.Author;
import ir.bigz.ms.searchfilter.author.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
@Slf4j
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{authorid}")
    public ResponseEntity<?> getAuthor(@PathVariable("authorid") long authorId){
        Author author = authorService.getAuthor(authorId);
        log.info("author: " + author.toString());
        return new ResponseEntity<>(author, HttpStatus.ACCEPTED);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getBookByFilter(
            @RequestParam int page,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName){
        AuthorFilterRequest authorFilterRequest = new AuthorFilterRequest();
        authorFilterRequest.setFirstName(firstName);
        authorFilterRequest.setLastName(lastName);
        Page<Author> books = authorService.getByFilter(page, authorFilterRequest);
        return new ResponseEntity<>(books, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Author author){
        authorService.addAuthor(author);
        return new ResponseEntity("add success", HttpStatus.ACCEPTED);
    }
}
