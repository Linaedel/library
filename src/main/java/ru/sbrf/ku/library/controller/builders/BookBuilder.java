package ru.sbrf.ku.library.controller.builders;

import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.entities.Book;

import java.util.ArrayList;

public class BookBuilder {
    private Book book;
    private BookDao bookDao;

    public BookBuilder(BookDao bookDao) {
        this.book = new Book();
        this.bookDao = bookDao;
    }

    public Book build() {
        book.setMovements(new ArrayList<>());
        bookDao.add(book);
        return book;
    }

    public BookBuilder setName(String name){
        book.setName(name);
        return this;
    }

    public BookBuilder setIsbn( String isbn ) {
        book.setIsbn(isbn);
        return this;
    }

    public BookBuilder setAuthor( String author ) {
        book.setAuthor(author);
        return this;
    }
}