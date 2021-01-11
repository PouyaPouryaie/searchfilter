package ir.bigz.ms.searchfilter.person.service;

import ir.bigz.ms.searchfilter.book.filter.BookFilterRequest;
import ir.bigz.ms.searchfilter.book.model.Book;
import ir.bigz.ms.searchfilter.person.filter.PersonFilterRequest;
import ir.bigz.ms.searchfilter.person.model.Person;
import ir.bigz.ms.searchfilter.person.model.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface PersonService {

    PersonDTO getPerson(long personId);

    void addPerson(Person person);

    Page<Person> getByFilter(int pageNumber, PersonFilterRequest personFilterRequest);
}
