package ru.sbrf.ku.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.dao.impl.BookDaoImpl;
import ru.sbrf.ku.library.entities.Book;
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

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public String book(@PathVariable("id") Long id, ModelMap modelMap ) {
        modelMap.addAttribute( "book", bookService.get( id ) );
        return "book";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String bookEdit(@ModelAttribute("book") Book book, ModelMap modelMap ) {
        bookService.update( book );
        modelMap.addAttribute( "book", bookService.get( book.getId() ) );
        return "book";
    }
}
