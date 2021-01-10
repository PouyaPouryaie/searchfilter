package ir.bigz.ms.searchfilter.common.service;

import ir.bigz.ms.searchfilter.author.repository.AuthorRepository;
import ir.bigz.ms.searchfilter.book.repository.BookRepository;
import ir.bigz.ms.searchfilter.common.utility.PageableUtility;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

    @Autowired
    protected AuthorRepository authorRepository;

    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    protected PageableUtility pageableUtility;
}
