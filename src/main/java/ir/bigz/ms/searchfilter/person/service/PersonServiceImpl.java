package ir.bigz.ms.searchfilter.person.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.bigz.ms.searchfilter.common.service.BaseService;
import ir.bigz.ms.searchfilter.person.filter.PersonFilterRequest;
import ir.bigz.ms.searchfilter.person.model.Person;
import ir.bigz.ms.searchfilter.person.model.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PersonServiceImpl extends BaseService implements PersonService {

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
    public Page<Person> getByFilter(int pageNumber, PersonFilterRequest personFilterRequest) {
        return null;
    }

    private PersonDTO convertPerson(Person person){
        return new ObjectMapper().convertValue(person, PersonDTO.class);
    }
}
