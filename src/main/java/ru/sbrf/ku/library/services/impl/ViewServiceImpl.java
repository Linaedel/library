package ru.sbrf.ku.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import ru.sbrf.ku.library.authfacade.AuthenticationFacade;
import ru.sbrf.ku.library.entities.*;
import ru.sbrf.ku.library.services.*;
import ru.sbrf.ku.library.view.AvailableBooksView;
import ru.sbrf.ku.library.view.ReturnedBooksView;

import java.util.Collection;

@Service
public class ViewServiceImpl implements ViewService {
    BookService bookService;
    PersonService personService;
    PlacementService placementService;
    MovementService movementService;

    @Autowired
    public ViewServiceImpl(BookService bookService, PersonService personService, PlacementService placementService, MovementService movementService) {
        this.bookService = bookService;
        this.personService = personService;
        this.placementService = placementService;
        this.movementService = movementService;
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
        return new ModelAndViewBuilder().setViewName(resource).addObject("holdedBooks", bookService.getBooksOnHolder(currentUser)).build();
    }

    @Override
    public ModelAndView returnBook(String resource, Long id) {
        Book returnedBook = bookService.get(id);
        Person currentUser = personService.findByUsername(new AuthenticationFacade().getAuthentication().getName());
        returnedBook.setReturned(1);
        returnedBook.setReturner(currentUser);
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

    @Override
    public ModelAndView getReturnedBooks(String resource) {
        Collection<Book> returnedBooks = bookService.getReturnedBooks();
        Collection<Placement> placements = placementService.list();
        ReturnedBooksView view = new ReturnedBooksView(returnedBooks.size(), returnedBooks,placements);
        return new ModelAndViewBuilder().setViewName(resource).addObject("returnedBooks", view).build();
    }

    @Override
    @Transactional
    public ModelAndView getBook(String resource, Long bookId, Long placementId) {
        Book book = bookService.get(bookId);
        Movement lastMovement = bookService.getLastMovement(book);
        Movement move = new Movement();
        move.setBook(book);
        move.setFrom(lastMovement.getTo());
        move.setTo(placementService.get(placementId));
//        movementService.add(move);
        book.getMovements().add(move);
        book.getDescription().setAvailable(book.getDescription().getAvailable() + 1);
        book.setReturner(null);
        book.setReturned(0);
        bookService.update(book);
        return getReturnedBooks(resource);
    }
}
