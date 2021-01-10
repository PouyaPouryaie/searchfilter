package ir.bigz.ms.searchfilter.book.service;

import ir.bigz.ms.searchfilter.book.filter.BookFilterRequest;
import ir.bigz.ms.searchfilter.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    Book getBook(long bookId);

    void addBook(Book book);

    Page<Book> getByFilter(int pageNumber, BookFilterRequest bookFilterRequest);
}
