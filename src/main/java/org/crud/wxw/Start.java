package org.crud.wxw;

import org.crud.wxw.service.BookService;

public class Start {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        System.out.println(bookService.getBooksByPersonId(6L));
        System.out.println(bookService.getBookOwner(8L));
    }
}
