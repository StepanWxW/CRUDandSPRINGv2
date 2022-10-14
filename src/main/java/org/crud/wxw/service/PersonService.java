package org.crud.wxw.service;

import org.crud.wxw.model.Person;
import org.crud.wxw.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @Transactional
    public Person save(Person person) {
        personRepository.save(person);
        return person;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }
    @Transactional
    public void update(Long id, Person person) {
        person.setId(id);
        personRepository.save(person);
    }
    @Transactional
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

}
