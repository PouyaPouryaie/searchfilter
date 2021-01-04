package ir.bigz.ms.searchfilter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String sbn;
    private String author;

    public Book(Long id, String title, String sbn, String author) {
        this.id = id;
        this.title = title;
        this.sbn = sbn;
        this.author = author;
    }
}
