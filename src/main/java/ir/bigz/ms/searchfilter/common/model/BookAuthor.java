package ir.bigz.ms.searchfilter.common.model;

import ir.bigz.ms.searchfilter.author.model.Author;
import ir.bigz.ms.searchfilter.book.model.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
public class BookAuthor {

    @EmbeddedId
    BookAuthorKey id;

    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    @ManyToOne
    Book book;

    @MapsId("authorId")
    @JoinColumn(name = "author_id")
    @ManyToOne
    Author author;
}
