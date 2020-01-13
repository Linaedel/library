package ru.sbrf.ku.library.services;

import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.BookDescription;
import ru.sbrf.ku.library.entities.Holder;
import ru.sbrf.ku.library.entities.Person;

import java.util.Collection;

public interface BookService {

    Collection<BookDescription> getBookDescriptions();

    BookDescription getBookDescription(Long id);

    Collection<BookDescription> getAvailabeBooks();

    Book get(Long id);

    void update(Book book);

    void updateBookDescription(BookDescription description);

    void add(Book book);

    void request(Long id, Person person);

    Collection<Book> getBooksOnHolder(Holder holder);
}
