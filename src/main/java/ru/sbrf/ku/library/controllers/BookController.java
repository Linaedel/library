package ru.sbrf.ku.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.sbrf.ku.library.entities.BookDescription;
import ru.sbrf.ku.library.services.BookService;
import ru.sbrf.ku.library.services.ViewService;

@Controller
public class BookController {
    private final BookService bookService;
    private final ViewService viewService;

    @Autowired
    public BookController( BookService bookService, ViewService viewService) {
        this.bookService = bookService;
        this.viewService = viewService;
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
        modelMap.addAttribute( "books", bookService.getBookDescriptions() );
        return "books";
    }

    @RequestMapping(value = "/availablebooks")
    public ModelAndView availableBooks(ModelMap modelMap) {
        return viewService.getAvailableBooks("availablebooks");
    }

    @RequestMapping(value = "/availablebooks/{id}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView requestBook(@PathVariable("id") Long id, ModelMap modelMap){
        return viewService.requestBook("availablebooks",id);
    }

    @RequestMapping(value = "/holdedbooks")
    public ModelAndView holdedBooks(ModelMap modelMap) {
        return viewService.getHoldedBooks("holdedbooks");
    }

    @RequestMapping(value = "/holdedbooks/{id}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView returnBook(@PathVariable("id") Long id, ModelMap modelMap){
        return viewService.returnBook("holdedbooks",id);
    }
}
