package ru.sbrf.ku.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import ru.sbrf.ku.library.authfacade.AuthenticationFacade;
import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.BookDescription;
import ru.sbrf.ku.library.entities.Person;
import ru.sbrf.ku.library.services.BookService;
import ru.sbrf.ku.library.services.PersonService;
import ru.sbrf.ku.library.services.ViewService;
import ru.sbrf.ku.library.view.AvailableBooksView;
import ru.sbrf.ku.library.view.HoldedBooksView;

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
        return new ModelAndViewBuilder().setViewName(resource).addObject("availableBooks", view).build();
    }

    @Override
    public ModelAndView requestBook(String resource, Long id) {
        Person currentUser = personService.findByUsername(new AuthenticationFacade().getAuthentication().getName());
        bookService.request(id,currentUser);
        return getAvailableBooks(resource);
    }

    @Override
    public ModelAndView getHoldedBooks(String resource) {
        Person currentUser = personService.findByUsername(new AuthenticationFacade().getAuthentication().getName());
        HoldedBooksView view = new HoldedBooksView(bookService.getBooksOnHolder(currentUser));
        return new ModelAndViewBuilder().setViewName(resource).addObject("holdedBooks", view).build();
    }

    @Override
    public ModelAndView returnBook(String resource, Long id) {
        Book returnedBook = bookService.get(id);
        returnedBook.setReturned(1);
        bookService.update(returnedBook);
        return getHoldedBooks(resource);
    }

    static class ModelAndViewBuilder{
        private ModelAndView mv;

        public ModelAndViewBuilder() {
            this.mv = new ModelAndView();
        }

        public ModelAndViewBuilder setViewName(String resource){
            this.mv.setViewName(resource);
            return this;
        }

        public ModelAndViewBuilder addObject(String attributeName, @Nullable Object attributeValue){
            this.mv.addObject(attributeName,attributeValue);
            return this;
        }

        public ModelAndView build(){
            return this.mv;
        }
    }
}
