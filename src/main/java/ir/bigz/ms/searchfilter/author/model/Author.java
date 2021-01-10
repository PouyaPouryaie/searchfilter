package ir.bigz.ms.searchfilter.author.model;

import ir.bigz.ms.searchfilter.book.model.Book;
import ir.bigz.ms.searchfilter.common.model.BaseEntity;
import ir.bigz.ms.searchfilter.common.model.BookAuthor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
public class Author extends BaseEntity<Long> implements Serializable {

    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();
}
