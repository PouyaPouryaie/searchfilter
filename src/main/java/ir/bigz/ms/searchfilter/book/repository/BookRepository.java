package ir.bigz.ms.searchfilter.book.repository;

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
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    default Page<Book> findBookByFilters(BookFilterRequest bookFilterRequest, Pageable pageable){
        return findAll((root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(!StringUtils.isEmpty(bookFilterRequest.getTitle())){
                Predicate title = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + bookFilterRequest.getTitle().toLowerCase() + "%");
                predicates.add(title);
            }

            if(!StringUtils.isEmpty(bookFilterRequest.getSbn())){
                Predicate sbn = criteriaBuilder.like(criteriaBuilder.lower(root.get("sbn")),
                        "%" + bookFilterRequest.getSbn().toLowerCase() + "%");
                predicates.add(sbn);
            }

            predicates.add(criteriaBuilder.equal(root.get("deleted"), false));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }
}
