package ru.sbrf.ku.library;

import org.h2.tools.Server;
import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.dao.ClientDao;
import ru.sbrf.ku.library.dao.impl.BookDaoImpl;
import ru.sbrf.ku.library.dao.impl.ClientDaoImpl;
import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.Client;
import ru.sbrf.ku.library.utils.EntityManagerBuilder;

import java.sql.SQLException;

public class Starter {

    public static void main(String[] args) throws SQLException {
        Server server = Server.createTcpServer().start();
        ClientDao clientDao = new ClientDaoImpl();
//        clientDao.list().forEach(c -> System.out.println(c));
        BookDao bookDao = new BookDaoImpl();

        Client client1 = new Client();
        client1.setName("Test3");
        client1.setPhone("12");
        clientDao.insert(client1);

        Client client2 = new Client();
        client2.setName("Test4");
        client2.setPhone("21");
        clientDao.insert(client2);

        Book book = new Book();
        book.setName("Сто лет одиночества");
        book.setAuthor("Г.Г. Маркес");
        book.setIsbn("12345");
        bookDao.add(book);
        bookDao.list().forEach(c -> System.out.println(c));



        System.out.println("Client 1 list = " + bookDao.getBookOnHolder(client1.getId()));
        System.out.println("Client 2 list = " +bookDao.getBookOnHolder(client2.getId()));
        ((BookDaoImpl) bookDao).move(book,client1);
        System.out.println("Client 1 list = " + bookDao.getBookOnHolder(client1.getId()));
        System.out.println("Client 2 list = " +bookDao.getBookOnHolder(client2.getId()));
        ((BookDaoImpl) bookDao).move(book,client2);
        System.out.println("Client 1 list = " + bookDao.getBookOnHolder(client1.getId()));
        System.out.println("Client 2 list = " +bookDao.getBookOnHolder(client2.getId()));
        ((BookDaoImpl) bookDao).move(book,client1);
        System.out.println("Client 1 list = " + bookDao.getBookOnHolder(client1.getId()));
        System.out.println("Client 2 list = " +bookDao.getBookOnHolder(client2.getId()));

        book.getMovements().forEach(movement -> System.out.println(movement.getFrom() + " " + movement.getTo()));
//        clientDao.list().forEach(c -> System.out.println(c));
        server.stop();
    }


}


