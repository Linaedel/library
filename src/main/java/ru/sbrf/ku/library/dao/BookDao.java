package ru.sbrf.ku.library.dao;

import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.BookDescription;
import ru.sbrf.ku.library.entities.Movement;

import java.util.List;

public interface BookDao extends AbstractDao{
    List<Book> list();
    List<BookDescription> listOfBookDescriptions();
    List<BookDescription> listOfAvailableBooks();
    Book get (Long id);
    BookDescription getBookDescription(Long id);
    void update (Book book);
    void updateBookDescription(BookDescription description);
    void remove(Long id);
    List<Book> getBooksOnHolder(Long holderId);
    List<Book> getBooksOnHolders();
    Movement getLastMovement(Book book);
}
