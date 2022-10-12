package org.crud.wxw.repository.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.crud.wxw.entity.PersonEntity;
import org.crud.wxw.repository.PersonRepository;
import org.crud.wxw.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PersonRepositoryImpl implements PersonRepository {
    SessionUtil sessionUtil = new SessionUtil();
    public PersonEntity create(PersonEntity person) {
        Session session = sessionUtil.openTransactionSession();
        session.persist(person);
        session.getTransaction().commit();
        session.close();
        return person;
    }

    public List<PersonEntity> getAll() {
        List<PersonEntity> personList;
        Session session = sessionUtil.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<PersonEntity> cq = cb.createQuery(PersonEntity.class);
        cq.from(PersonEntity.class);
        personList = session.createQuery(cq).getResultList();
        session.close();
        return personList;
    }

    public PersonEntity getById(Long id) {
        Session session = sessionUtil.openSession();
        PersonEntity person = session.get(PersonEntity.class, id);
        session.close();
        return person;
    }

    public PersonEntity update(PersonEntity people) {
        try(Session session = sessionUtil.openTransactionSession()) {
            session.saveOrUpdate(people);
            session.getTransaction().commit();
        } catch (EntityNotFoundException e) {
            System.out.println("Person whit id: " + people.getId() + " is missing.");
        }
        return people;
    }

    public void delete(Long id) {
        try(Session session = sessionUtil.openTransactionSession()) {
            PersonEntity person = session.load(PersonEntity.class, id);
            session.delete(person);
            session.getTransaction().commit();
        } catch (EntityNotFoundException e) {
            System.out.println("People whit id: " + id + " is missing.");
        }
    }
}
