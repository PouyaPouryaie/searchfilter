package ir.bigz.ms.searchfilter.author.service;

import ir.bigz.ms.searchfilter.author.filter.AuthorFilterRequest;
import ir.bigz.ms.searchfilter.author.model.Author;
import ir.bigz.ms.searchfilter.common.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class AuthorServiceImpl extends BaseService implements AuthorService{

    @Override
    public Author getAuthor(long authorId) {
        return authorRepository.getAuthorById(authorId);
    }

    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Page<Author> getByFilter(int pageNumber, AuthorFilterRequest authorFilterRequest) {
        Pageable pageable;

        if(pageNumber != -1){
            pageable = pageableUtility.createPageable(pageNumber, 20);
        }
        else{
            pageable = PageRequest.of(0, 20);
        }

        return authorRepository.findAuthorByFilter(authorFilterRequest, pageable);
    }
}
