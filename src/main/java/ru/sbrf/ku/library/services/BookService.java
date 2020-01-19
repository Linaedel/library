package ru.sbrf.ku.library.services;

import ru.sbrf.ku.library.entities.*;

import java.util.Collection;

public interface BookService {

    Collection<BookDescription> getBookDescriptions();

    BookDescription getBookDescription(Long id);

    Collection<BookDescription> getAvailabeBooks();

    Collection<BookDescription> getRequestedBooks();

    Book get(Long id);

    void update(Book book);

    void updateBookDescription(BookDescription description);

    void add(Book book);

    Collection<Book> getBooksOnHolder(Holder holder);

    Collection<Book> getReturnedBooks();

    Movement getLastMovement(Book book);

    Collection<Book> bookList();

    void remove(Long id);
}
