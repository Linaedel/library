package ru.sbrf.ku.library.controller;

import ru.sbrf.ku.library.controller.builders.BookBuilder;
import ru.sbrf.ku.library.controller.builders.ClientBuilder;
import ru.sbrf.ku.library.controller.builders.PlacementBuilder;
import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.dao.ClientDao;
import ru.sbrf.ku.library.dao.PlacementDao;
import ru.sbrf.ku.library.dao.impl.BookDaoImpl;
import ru.sbrf.ku.library.dao.impl.ClientDaoImpl;
import ru.sbrf.ku.library.dao.impl.PlacementDaoImpl;
import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.Client;
import ru.sbrf.ku.library.entities.Holder;
import ru.sbrf.ku.library.entities.Movement;

import java.sql.SQLException;
import java.util.List;

public class LibraryController {
    private BookDao bookDao = new BookDaoImpl();
    private ClientDao clientDao = new ClientDaoImpl();
  private PlacementDao placementDao = new PlacementDaoImpl();

    public ClientBuilder addClient () {
        return new ClientBuilder(clientDao);
    }

    public BookBuilder addBook()  {
        return new BookBuilder(bookDao);
    }

    public PlacementBuilder addPlacement(){
        return new PlacementBuilder(placementDao);
    }

    public List<Client> getClientList() throws SQLException {
        return clientDao.list();
    }

    public List<Book> getBookList(){
        return bookDao.list();
    }

    public List<Book> getBooksOnHolder(Holder holder){
        return bookDao.getBooksOnHolder(holder.getId());
    }


    public void move(Book book, Holder holder) {
        Movement movement = new Movement();
        movement.setBook(book);
        movement.setFrom(
                bookDao.getLastMovement(book)==null?
                        null:
                        bookDao.getLastMovement(book).getTo()
        );
        movement.setTo(holder);
        book.getMovements().add(movement);
        bookDao.update(book);
    }

    public ClientDao clentDao (){
        return clientDao;
    }

    public BookDao bookDao () {
        return bookDao;
    }
}
