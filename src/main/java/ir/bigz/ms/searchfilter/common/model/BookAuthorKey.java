package ir.bigz.ms.searchfilter.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

//@Embeddable
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class BookAuthorKey implements Serializable {

    @Column(name = "author_id")
    Long authorId;

    @Column(name = "book_id")
    Long bookId;
}
