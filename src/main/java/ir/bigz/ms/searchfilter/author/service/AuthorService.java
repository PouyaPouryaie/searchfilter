package ir.bigz.ms.searchfilter.author.service;

import ir.bigz.ms.searchfilter.author.filter.AuthorFilterRequest;
import ir.bigz.ms.searchfilter.author.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {

    Author getAuthor(long authorId);

    void addAuthor(Author author);

    Page<Author> getByFilter(int pageNumber, AuthorFilterRequest authorFilterRequest);
}
