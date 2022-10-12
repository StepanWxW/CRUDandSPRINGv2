package org.crud.wxw.repository.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.crud.wxw.entity.BookEntity;

import org.crud.wxw.repository.BookRepository;
import org.crud.wxw.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BookRepositoryImpl implements BookRepository {
    SessionUtil sessionUtil = new SessionUtil();
    public BookEntity create(BookEntity book) {
        Session session = sessionUtil.openTransactionSession();
        session.persist(book);
        session.getTransaction().commit();
        session.close();
        return book;
    }

    public List<BookEntity> getAll() {
        List<BookEntity> bookList;
        Session session = sessionUtil.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<BookEntity> cq = cb.createQuery(BookEntity.class);
        cq.from(BookEntity.class);
        bookList = session.createQuery(cq).getResultList();
        session.close();
        return bookList;
    }

    public BookEntity getById(Long id) {
        Session session = sessionUtil.openSession();
        BookEntity book = session.get(BookEntity.class, id);
        session.close();
        return book;
    }

    public BookEntity update(BookEntity book) {
        try(Session session = sessionUtil.openTransactionSession()) {
            session.saveOrUpdate(book);
            session.getTransaction().commit();
        } catch (EntityNotFoundException e) {
            System.out.println("Book whit id: " + book.getId() + " is missing.");
        }
        return book;
    }

    public void delete(Long id) {
        try(Session session = sessionUtil.openTransactionSession()) {
            BookEntity book = session.load(BookEntity.class, id);
            session.delete(book);
            session.getTransaction().commit();
        } catch (EntityNotFoundException e) {
            System.out.println("Book whit id: " + id + " is missing.");
        }
    }
    public void release (Long id) {
        try(Session session = sessionUtil.openTransactionSession()) {
            BookEntity book = session.get(BookEntity.class, id);
            book.setPersonEntity(null);
            session.getTransaction().commit();
        } catch (EntityNotFoundException e) {
            System.out.println("Book is missing.");
        }
    }
}
