package ru.sbrf.ku.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.sbrf.ku.library.services.ViewService;

@Controller
public class BookController {
    private final ViewService viewService;

    @Autowired
    public BookController(ViewService viewService) {
        this.viewService = viewService;
    }

    @RequestMapping(value = "/books")
    public ModelAndView books( ModelMap modelMap ) {
        return viewService.getBookDescriptions("books");
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView book(@PathVariable("id") Long id, ModelMap modelMap ) {
        return viewService.getBookDetails("book",id);
    }

    @RequestMapping(value = "/book/del/{id}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView removeBook(@PathVariable("id") Long id, ModelMap modelMap ) {
        return viewService.removeBook("book",id);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public ModelAndView bookEdit(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("author") String author,
            @RequestParam("isbn") String isbn,
            ModelMap modelMap ) {
        return viewService.updateBookDetails("book",id,name,author,isbn);
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

    @RequestMapping(value = "/returnedbooks")
    public ModelAndView returnedBooks(ModelMap modelMap) {
        return viewService.getReturnedBooks("returnedbooks");
    }

    @RequestMapping(value = "/returnedbooks/take", params = {"book","place"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView getBook(@RequestParam("book") Long bookId, @RequestParam("place") Long placementId, ModelMap modelMap){
        return viewService.getBook("returnedbooks",bookId,placementId);
    }

    @RequestMapping(value = "/requestedbooks")
    public ModelAndView requestedBooks(ModelMap modelMap) {
        return viewService.getRequestedBooks("requestedbooks");
    }

    @RequestMapping(value = "/requestedbooks/give", params = {"book","person"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView giveBook(@RequestParam("book") Long bookId, @RequestParam("person") Long personId, ModelMap modelMap){
        return viewService.giveBook("requestedbooks",bookId,personId);
    }

}
