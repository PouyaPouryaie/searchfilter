package ir.bigz.ms.searchfilter.person.controller;

import ir.bigz.ms.searchfilter.person.filter.PersonFilterRequest;
import ir.bigz.ms.searchfilter.person.model.Person;
import ir.bigz.ms.searchfilter.person.model.PersonDTO;
import ir.bigz.ms.searchfilter.person.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/person")
@Slf4j
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{personid}")
    public ResponseEntity<?> getPerson(@PathVariable("personid") long personId){
        PersonDTO personDTO = personService.getPerson(personId);
        log.info("person: " + personDTO.toString());
        return new ResponseEntity<>(personDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getPersonByFilter(
            @RequestParam int page,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName){
        PersonFilterRequest personFilterRequest = new PersonFilterRequest();
        personFilterRequest.setFirstName(firstName);
        personFilterRequest.setLastName(lastName);
        Page<PersonDTO> persons = personService.getByFilter(page, personFilterRequest);
        return new ResponseEntity<>(persons, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPerson(@Valid @RequestBody Person person){
        personService.addPerson(person);
        return new ResponseEntity("add success", HttpStatus.ACCEPTED);
    }
}
