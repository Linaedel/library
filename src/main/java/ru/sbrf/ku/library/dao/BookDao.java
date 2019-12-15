package ru.sbrf.ku.library.dao;

import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.BookDescription;
import ru.sbrf.ku.library.entities.Movement;

import java.util.List;

public interface BookDao extends AbstractDao{
    List<Book> list();
//    List<String> listOfBookNames();
    List<BookDescription> listOfBookNames();
    Book get (Long id);
    void update (Book book);
    List<Book> getListByISBN (String isbn);
    String getISBNbyBookName (String name);
    void remove(Long id);
    List<Book> getBooksOnHolder(Long holderId);
    Movement getLastMovement(Book book);
}
