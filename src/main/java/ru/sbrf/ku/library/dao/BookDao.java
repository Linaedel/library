package ru.sbrf.ku.library.dao;

import ru.sbrf.ku.library.entities.Book;

import java.util.List;

public interface BookDao {
    void add(Book book);
    List<Book> list();
    Book get (Long id);
    void remove(Long id);
    List<Book> getBookOnHolder(Long holderId);
}
