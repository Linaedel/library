package ru.sbrf.ku.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import ru.sbrf.ku.library.authfacade.AuthenticationFacade;
import ru.sbrf.ku.library.entities.BookDescription;
import ru.sbrf.ku.library.entities.Person;
import ru.sbrf.ku.library.services.BookService;
import ru.sbrf.ku.library.services.PersonService;
import ru.sbrf.ku.library.services.ViewService;
import ru.sbrf.ku.library.view.AvailableBooksView;

import java.util.Collection;

@Service
public class ViewServiceImpl implements ViewService {
    BookService bookService;
    PersonService personService;

    @Autowired
    public ViewServiceImpl(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @Override
    public ModelAndView getAvailableBooks(String resource) {
        Collection<BookDescription> availableBooks = bookService.getAvailabeBooks();
        Person currentUser = personService.findByUsername(new AuthenticationFacade().getAuthentication().getName());
        AvailableBooksView view = new AvailableBooksView(availableBooks, currentUser);
        ModelAndView mv = new ModelAndView();
        mv.addObject("availableBooks", view);
        mv.setViewName(resource);
        return mv;
    }
}
