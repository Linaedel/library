package ru.sbrf.ku.library.controllers;

import org.h2.tools.Server;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.Client;
import ru.sbrf.ku.library.entities.Placement;

import java.sql.SQLException;
import java.util.List;

@Controller
public class LibraryController {
    Server server;

    @RequestMapping(value = "/")
    public String index(ModelMap modelMap) throws SQLException {
//        server = Server.createTcpServer().start();

        modelMap.addAttribute("message","Hello, world!");
        return "index";
    }

    @RequestMapping(value = "/stop")
    public String stopServer(ModelMap modelMap){
//        server.stop();
        return "index";
    }

    @RequestMapping(value = "/init")
    public String dbInit(ModelMap modelMap) throws SQLException {
        ru.sbrf.ku.library.controller.LibraryController lc = new ru.sbrf.ku.library.controller.LibraryController();
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
        lc.getBookList().forEach(book -> System.out.println(book.getDescription().getName()));
        List<Book> books = lc.getBooksOnHolder(client1);
        System.out.println(books);

        lc.move(book1,shelf1);
        lc.move(book2,shelf2);
        lc.move(book3,shelf1);
        lc.move(book1, client1);
        lc.move(book1, shelf2);
        lc.move(book1, client1);
        lc.move(book2, client2);
        lc.move(book4,shelf1);
        return "index";
    }
}
