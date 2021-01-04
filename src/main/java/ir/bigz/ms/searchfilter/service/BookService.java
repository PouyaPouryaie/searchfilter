package ir.bigz.ms.searchfilter.service;

import ir.bigz.ms.searchfilter.filter.BookFilterRequest;
import ir.bigz.ms.searchfilter.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    Book getBook(long bookId);

    void addBook(Book book);

    Page<Book> getByFilter(int pageNumber, BookFilterRequest bookFilterRequest);
}
