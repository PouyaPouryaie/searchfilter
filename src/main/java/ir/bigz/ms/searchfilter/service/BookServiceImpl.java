package ir.bigz.ms.searchfilter.service;

import ir.bigz.ms.searchfilter.filter.BookFilterRequest;
import ir.bigz.ms.searchfilter.filter.PageableUtility;
import ir.bigz.ms.searchfilter.model.Book;
import ir.bigz.ms.searchfilter.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final PageableUtility pageableUtility;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, PageableUtility pageableUtility) {
        this.bookRepository = bookRepository;
        this.pageableUtility = pageableUtility;
    }

    @Override
    public Book getBook(long bookId) {
        Book book = bookRepository.findById(bookId).get();
        return book;
    }

    public Page<Book> getByFilter(int pageNumber, BookFilterRequest bookFilterRequest){

        Pageable pageable;

        if(pageNumber != -1){
            pageable = pageableUtility.createPageable(pageNumber, 20);
        }
        else{
            pageable = PageRequest.of(0, 20);
        }

        Page<Book> bookByFilters = bookRepository.findBookByFilters(bookFilterRequest, pageable);
        return bookByFilters;
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }
}
