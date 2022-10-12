package org.crud.wxw.mapper;

import org.crud.wxw.entity.BookEntity;
import org.crud.wxw.entity.PersonEntity;
import org.crud.wxw.model.Book;
import org.crud.wxw.model.Person;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {


    public static Book toModel(BookEntity bookEntity) {
        Book book = new Book();
        book.setId(bookEntity.getId());
        book.setName(bookEntity.getName());
        book.setAuthor(bookEntity.getAuthor());
        book.setYear(bookEntity.getYaer());
        if(bookEntity.getPersonEntity() !=null) {
            book.setPerson(PersonMapper.toModel(new PersonEntity(bookEntity.getPersonEntity().getId())));
        }
        return book;
    }
    public BookEntity toModelEntity(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setName(book.getName());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setYaer(book.getYear());
        if (book.getPerson() !=null) {
            bookEntity.setPersonEntity(PersonMapper.toModelEntity(book.getPerson()));
        }
        return bookEntity;
    }
}
