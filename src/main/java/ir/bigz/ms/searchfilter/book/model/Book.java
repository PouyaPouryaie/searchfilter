package ir.bigz.ms.searchfilter.book.model;

import ir.bigz.ms.searchfilter.author.model.Author;
import ir.bigz.ms.searchfilter.common.model.BaseEntity;
import ir.bigz.ms.searchfilter.common.model.BookAuthor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Access(AccessType.FIELD)
@AttributeOverride(name = "id", column = @Column(name = "book_id"))
public class Book extends BaseEntity<Long> {

    private String title;
    private String sbn;
    @Enumerated(EnumType.STRING) // select type of enum persist in db
    private BookState bookState;

//    @OneToMany(mappedBy = "book")
//    private Set<BookAuthor> bookAuthors;

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> author = new HashSet<>();

    public Book( String title, String sbn, String author, BookState bookState) {
        this.title = title;
        this.sbn = sbn;
        this.bookState = bookState;
    }
}
