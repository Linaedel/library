package ru.sbrf.ku.library.services;

import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.BookDescription;

import java.util.Collection;

public interface BookService {

    Collection<BookDescription> getBookDescriptions();

    Book get(Long id);

    void update(Book book);

    void add(Book book);
}
