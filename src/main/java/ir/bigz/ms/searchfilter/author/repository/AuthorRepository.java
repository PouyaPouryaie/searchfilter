package ir.bigz.ms.searchfilter.author.repository;

import ir.bigz.ms.searchfilter.author.filter.AuthorFilterRequest;
import ir.bigz.ms.searchfilter.author.model.Author;
import ir.bigz.ms.searchfilter.book.filter.BookFilterRequest;
import ir.bigz.ms.searchfilter.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    Author getAuthorById(Long authorId);

    default Page<Author> findAuthorByFilter(AuthorFilterRequest authorFilterRequest, Pageable pageable){
        return findAll((root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(!StringUtils.isEmpty(authorFilterRequest.getFirstName())){
                Predicate title = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("firstName")),
                        "%" + authorFilterRequest.getFirstName().toLowerCase() + "%");
                predicates.add(title);
            }

            if(!StringUtils.isEmpty(authorFilterRequest.getLastName())){
                Predicate sbn = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
                        "%" + authorFilterRequest.getLastName().toLowerCase() + "%");
                predicates.add(sbn);
            }

            predicates.add(criteriaBuilder.equal(root.get("deleted"), false));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }
}
