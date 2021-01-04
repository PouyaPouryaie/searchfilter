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
public class Book extends BaseEntity<Long> {

    private String title;
    private String sbn;
    private String author;

    public Book( String title, String sbn, String author) {
        this.title = title;
        this.sbn = sbn;
        this.author = author;
    }
}
