package ru.sbrf.ku.library.dao;

import ru.sbrf.ku.library.entities.*;

import java.util.List;

public interface BookDao extends AbstractDao{
    List<Book> list();
    List<Book> getBooksOnHolder(Holder holder);
    List<Book> getBooksOnHolders();
    List<Book> getBooksOnHands();
    List<Book> getReturnedBooks();

    Book get (Long id);
    void update (Book book);
    void remove(Long id);

    List<BookDescription> listOfBookDescriptions();
    List<BookDescription> listOfAvailableBooks();
    List<BookDescription> getRequestedBooks();

    BookDescription getBookDescription(Long id);
    void updateBookDescription(BookDescription description);

    Movement getLastMovement(Book book);
}
