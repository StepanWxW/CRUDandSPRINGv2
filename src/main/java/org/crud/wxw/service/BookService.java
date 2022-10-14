package org.crud.wxw.service;


import org.crud.wxw.model.Book;
import org.crud.wxw.model.Person;
import org.crud.wxw.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book save(Book book) {
        bookRepository.save(book);
        return book;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    @Transactional
    public void update(Long id, Book book) {
        Book bookToBeUpdated = bookRepository.findById(id).get();
        book.setId(id);
        bookToBeUpdated.setPerson(bookToBeUpdated.getPerson());
        bookRepository.save(bookToBeUpdated);
    }
    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(Long id) {
        return bookRepository.findAll().stream().
                filter((p) -> p.getPerson() != null && Objects.equals(p.getPerson().getId(), id)).toList();
    }


    public Person getBookOwner(Long id) {
        return bookRepository.findById(id).map(Book::getPerson).orElse(null);
    }
    @Transactional
    public void release(Long id) {
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setPerson(null);
                });
    }
}
