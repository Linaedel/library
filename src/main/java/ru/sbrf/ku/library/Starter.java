package ru.sbrf.ku.library;

import org.h2.tools.Server;
import ru.sbrf.ku.library.controller.LibraryController;
import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.Client;
import ru.sbrf.ku.library.entities.Holder;
import ru.sbrf.ku.library.entities.Placement;

import java.sql.SQLException;
import java.util.List;

public class Starter {

    public static void main(String[] args) throws SQLException {
        Server server = Server.createTcpServer().start();

        LibraryController lc = new LibraryController();
        Client client1 = lc.addClient().setName("Иванов").setAddress("Москва").setPhone("223322").build();
        Client client2 = lc.addClient().setName("Петров").setAddress("Тула").setPhone("123123").build();
        Placement shelf1 = lc.addPlacement().setName("Верхняя полка").setDescription("Тяжело тянуться").build();
        Placement shelf2 = lc.addPlacement().setName("Нижняя полка").setDescription("Опция по дефолту").build();

        Book book1 = lc.addBook().setName("Сто лет одиночества").setAuthor("Г.Г. Маркес").setIsbn("12").build();
        Book book2 = lc.addBook().setName("Горе от ума").setAuthor("А.С. Грибоедов").setIsbn("13").build();
        Book book3 = lc.addBook().setName("Не время для драконов").setAuthor("C. Лукьяненко, Н.Перумов").setIsbn("2222").build();
        Book book4 = lc.addBook().setName("Сто лет одиночества").setAuthor("Г.Г. Маркес").setIsbn("12").build();
        lc.addBook().setName("Сто лет одиночества").setAuthor("Г.Г. Маркес").setIsbn("12").build();

        System.out.println("Клиенты:");
        lc.getClientList().forEach(client -> System.out.println(client.getName()));
        System.out.println("Книги:");
        lc.getBookList().forEach(book -> System.out.println(book.getName()));
        List<Book> books = lc.getBooksOnHolder(client1);
        System.out.println(books);

        printState(lc,client1,client2,shelf1,shelf2);
        lc.move(book1,shelf1);
        lc.move(book2,shelf2);
        lc.move(book3,shelf1);
        printState(lc,client1,client2,shelf1,shelf2);
        lc.move(book1, client1);
        lc.move(book1, shelf2);
        lc.move(book1, client1);
        printState(lc,client1,client2,shelf1,shelf2);
        lc.move(book2, client2);
        printState(lc,client1,client2,shelf1,shelf2);
        lc.move(book4,shelf1);
        server.stop();
    }

    public static void printState(LibraryController lc, Holder holder1, Holder holder2, Holder holder3, Holder holder4){
        System.out.println("============================================");
        System.out.println("Client 1 list: ");
        lc.getBooksOnHolder(holder1).forEach(book -> System.out.println("\t" + book.getName()));
        System.out.println("Client 2 list: ");
        lc.getBooksOnHolder(holder2).forEach(book -> System.out.println("\t" + book.getName()));
        System.out.println("Shelf 1 list: ");
        lc.getBooksOnHolder(holder3).forEach(book -> System.out.println("\t" + book.getName()));
        System.out.println("Shelf 2 list: ");
        lc.getBooksOnHolder(holder4).forEach(book -> System.out.println("\t" + book.getName()));
    }
}


