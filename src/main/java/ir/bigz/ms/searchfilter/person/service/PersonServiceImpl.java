package ir.bigz.ms.searchfilter.person.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.bigz.ms.searchfilter.common.service.BaseService;
import ir.bigz.ms.searchfilter.person.filter.PersonFilterRequest;
import ir.bigz.ms.searchfilter.person.model.Address;
import ir.bigz.ms.searchfilter.person.model.Person;
import ir.bigz.ms.searchfilter.person.model.PersonDTO;
import ir.bigz.ms.searchfilter.person.model.Person_;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class PersonServiceImpl extends BaseService implements PersonService {

    @Autowired
    EntityManager entityManager;

    @Override
    public PersonDTO getPerson(long personId) {
        Person personById = personRepository.getPersonById(personId);
        return convertPerson(personById);
    }

    @Override
    public void addPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public Page<PersonDTO> getByFilter(int pageNumber, PersonFilterRequest personFilterRequest) {

        Pageable pageable;

        if(pageNumber != -1){
            pageable = pageableUtility.createPageable(pageNumber, 20);
        }
        else{
            pageable = PageRequest.of(0, 20);
        }

        List<PersonDTO> personByFilter = findPersonByFilter(personFilterRequest, entityManager);

        Page<PersonDTO> personPage = listToPage(pageable, personByFilter);

        return personPage;
    }

    private PersonDTO convertPerson(Person person){
        return new ObjectMapper().convertValue(person, PersonDTO.class);
    }

    private static List<PersonDTO> findPersonByFilter(PersonFilterRequest personFilterRequest, EntityManager entityManager){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PersonDTO> query = cb.createQuery(PersonDTO.class);
        Root<Person> personRoot = query.from(Person.class);

        Join<Person, Address> join = personRoot.join(Person_.address);

        query.multiselect(
                personRoot.get(Person_.id),
                personRoot.get(Person_.firstName),
                personRoot.get(Person_.lastName),
                personRoot.get(Person_.age),
                personRoot.get(Person_.nationalCode),
                personRoot.get(Person_.mobile),
                join.get("addressOne"),
                join.get("postalCode")
        ).distinct(true);

        query.where(
                cb.and(
                        null == personFilterRequest.getNationalCode() ? cb.conjunction() : cb.equal(personRoot.get(Person_.nationalCode), personFilterRequest.getNationalCode()),
                        null == personFilterRequest.getAge() ? cb.conjunction() : cb.equal(personRoot.get(Person_.age), personFilterRequest.getAge()),
                        null == personFilterRequest.getFirstName() ? cb.conjunction() : cb.equal(personRoot.get(Person_.firstName), personFilterRequest.getFirstName()),
                        null == personFilterRequest.getLastName() ? cb.conjunction() : cb.equal(personRoot.get(Person_.lastName), personFilterRequest.getLastName()),
                        null == personFilterRequest.getMobile() ? cb.conjunction() : cb.equal(personRoot.get(Person_.mobile), personFilterRequest.getMobile())
                )
        );

        query.orderBy(new OrderImpl(personRoot.get(Person_.id), false));
        TypedQuery<PersonDTO> typedQuery = entityManager.createQuery(query);
        List<PersonDTO> resultList = typedQuery.getResultList();
        return resultList;
    }

    private static <T> Page<T> listToPage(Pageable pageable, List<T> list) {
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
        Page<T> pages = new PageImpl<T>(list.subList(start, end), pageable, list.size());

        return pages;
    }
}
