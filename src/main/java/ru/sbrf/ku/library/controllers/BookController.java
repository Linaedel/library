package ru.sbrf.ku.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.dao.impl.BookDaoImpl;
import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.BookDescription;
import ru.sbrf.ku.library.services.BookService;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController( BookService bookService ) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/books")
    public String books( ModelMap modelMap ) {
        modelMap.addAttribute( "books", bookService.getBookDescriptions() );
        return "books";
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String book(@PathVariable("id") Long id, ModelMap modelMap ) {
        System.out.println(id);
        modelMap.addAttribute( "bookdescription", bookService.getBookDescription( id ) );
        return "book";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String bookEdit(@ModelAttribute("bookdescription") BookDescription bookDescription, ModelMap modelMap ) {
        System.out.println(bookDescription.getId());
        System.out.println(bookDescription.getName());
        bookService.updateBookDescription( bookDescription );
        modelMap.addAttribute( "book", bookService.getBookDescription( bookDescription.getId() ) );
        return "book";
    }
}
