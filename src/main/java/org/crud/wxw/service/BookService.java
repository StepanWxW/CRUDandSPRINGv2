package org.crud.wxw.service;

import jakarta.persistence.EntityNotFoundException;
import org.crud.wxw.entity.BookEntity;
import org.crud.wxw.entity.PersonEntity;
import org.crud.wxw.mapper.BookMapper;
import org.crud.wxw.mapper.PersonMapper;
import org.crud.wxw.model.Book;
import org.crud.wxw.model.Person;
import org.crud.wxw.repository.impl.BookRepositoryImpl;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class BookService {
     private BookRepositoryImpl bookRepository = new BookRepositoryImpl();
     private BookMapper bookMapper = new BookMapper();

    public BookService() {

    }


    public BookEntity create(Book book) {
        BookEntity bookEntity = bookMapper.toModelEntity(book);
        bookRepository.create(bookEntity);
        return bookEntity;
    }

    public List<Book> getAll() {
        return bookRepository.getAll().stream().map(BookMapper::toModel).collect(Collectors.toList());
    }

    public Book getById(Long id) {
        return bookMapper.toModel(bookRepository.getById(id));
    }
    public BookEntity update(Book book) {
        return bookRepository.update(bookMapper.toModelEntity(book));
    }

    public void delete(Long id) {
        bookRepository.delete(id);
    }

    public BookService(BookRepositoryImpl bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<Book> getBooksByPersonId(Long id) {
        List<BookEntity> bookEntities = bookRepository.getAll().stream().
                filter((p) -> p.getPersonEntity() != null && Objects.equals(p.getPersonEntity().getId(), id)).toList();
        return bookEntities.stream().map(BookMapper::toModel).collect(Collectors.toList());
    }

    public Person getBookOwner(Long id) {
        PersonEntity personEntity = bookRepository.getById(id).getPersonEntity();
        if (personEntity != null) {
            return PersonMapper.toModel(bookRepository.getById(id).getPersonEntity());
        }
        return null;
    }
    public void release(Long id) {
        bookRepository.release(id);
    }
}
