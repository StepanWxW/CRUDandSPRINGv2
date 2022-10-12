package org.crud.wxw.mapper;

import org.crud.wxw.entity.PersonEntity;
import org.crud.wxw.model.Person;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class PersonMapper {


    public static Person toModel(PersonEntity personEntity) {
        Person person = new Person();
        person.setId(personEntity.getId());
        person.setAge(personEntity.getAge());
        person.setName(personEntity.getName());
        if (personEntity.getBookListEntity() != null) {
            person.setBookList(personEntity.getBookListEntity().stream().map(BookMapper::toModel).collect(Collectors.toList()));
        }
        return person;
    }
    public static PersonEntity toModelEntity(Person person) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(person.getId());
        personEntity.setAge(person.getAge());
        personEntity.setName(person.getName());
        return personEntity;
    }
}
