package ir.bigz.ms.searchfilter.book.service;

import ir.bigz.ms.searchfilter.author.model.Author;
import ir.bigz.ms.searchfilter.book.filter.BookFilterRequest;
import ir.bigz.ms.searchfilter.common.model.BookAuthor;
import ir.bigz.ms.searchfilter.common.service.BaseService;
import ir.bigz.ms.searchfilter.common.utility.PageableUtility;
import ir.bigz.ms.searchfilter.book.model.Book;
import ir.bigz.ms.searchfilter.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BookServiceImpl extends BaseService implements BookService {

    @Override
    public Book getBook(long bookId) {
        return bookRepository.findById(bookId).get();
    }

    public Page<Book> getByFilter(int pageNumber, BookFilterRequest bookFilterRequest){

        Pageable pageable;

        if(pageNumber != -1){
            pageable = pageableUtility.createPageable(pageNumber, 20);
        }
        else{
            pageable = PageRequest.of(0, 20);
        }

        return bookRepository.findBookByFilters(bookFilterRequest, pageable);
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }
}
