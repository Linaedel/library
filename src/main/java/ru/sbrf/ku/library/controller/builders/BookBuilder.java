package ru.sbrf.ku.library.controller.builders;

import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.BookDescription;

import java.util.ArrayList;

public class BookBuilder {
    private Book book;
    private BookDao bookDao;
    private BookDescription description;

    public BookBuilder(BookDao bookDao) {
        this.book = new Book();
        this.bookDao = bookDao;
        this.description = new BookDescription();
    }

    public Book build() {
        book.setMovements(new ArrayList<>());
        book.setDescription(description);
        bookDao.add(book);
        return book;
    }

    public BookBuilder setName(String name){
        description.setName(name);
        return this;
    }

    public BookBuilder setIsbn( String isbn ) {
        description.setIsbn(isbn);
        return this;
    }

    public BookBuilder setAuthor( String author ) {
        description.setAuthor(author);
        return this;
    }
}