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
import ru.sbrf.ku.library.view.BookDetailsView;
import ru.sbrf.ku.library.view.RequestedBooksView;
import ru.sbrf.ku.library.view.ReturnedBooksView;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Transactional
    public ModelAndView requestBook(String resource, Long id) {
        Person currentUser = personService.findByUsername(new AuthenticationFacade().getAuthentication().getName());
        BookDescription requestedBook = bookService.getBookDescription(id);
        requestedBook.getRequesters().add(currentUser);
        requestedBook.setRequested(1);
        currentUser.getRequestedBooks().add(requestedBook);
        personService.updatePerson(currentUser);
        bookService.updateBookDescription(requestedBook);
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

    @Override
    public ModelAndView getReturnedBooks(String resource) {
        Collection<Book> returnedBooks = bookService.getReturnedBooks();
        Collection<Placement> placements = placementService.list();
        ReturnedBooksView view = new ReturnedBooksView(returnedBooks,placements);
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
        book.getMovements().add(move);
//        movementService.add(move);
//        book.getMovements().add(move);
        book.getDescription().setAvailable(book.getDescription().getAvailable() + 1);
        book.setReturner(null);
        book.setReturned(0);
        book.setOnHolder(1);
        bookService.update(book);
        return getReturnedBooks(resource);
    }

    @Override
    public ModelAndView getRequestedBooks(String resource) {
        RequestedBooksView view = new RequestedBooksView(bookService.getRequestedBooks());
        return new ModelAndViewBuilder().setViewName(resource).addObject("requestedBooks", view).build();
    }

    @Override
    @Transactional
    public ModelAndView giveBook(String resource, Long bookId, Long personId) {
        Person requester = personService.getPerson(personId);
        BookDescription bookDescription = bookService.getBookDescription(bookId);
        List<Book> booksToGive = bookDescription
                .getBooks().stream()
                .filter(book -> book.getOnHolder().equals(1))
                .collect(Collectors.toList());
        Book book = booksToGive.remove(0);
        if (booksToGive.size() == 0) {
            bookDescription.setAvailable(0);
        }
        Set<Person> requesters = bookDescription.getRequesters();
        System.out.println(requesters.size());
        requesters.remove(personService.getPerson(personId));
        System.out.println(requesters.size());
        if(requesters.size() == 0) {
            bookDescription.setRequested(0);
        }
        bookDescription.setRequesters(requesters);
        book.setOnHolder(0);
        requester.getRequestedBooks().remove(bookDescription);
        Movement lastMovement = bookService.getLastMovement(book);
        Movement move = new Movement();
        move.setBook(book);
        move.setFrom(lastMovement.getTo());
        move.setTo(requester);
        book.getMovements().add(move);
//        movementService.add(move);
        personService.updatePerson(requester);
        bookService.updateBookDescription(bookDescription);
        bookService.update(book);
        return getRequestedBooks(resource);
    }

    @Override
    public ModelAndView getBookDescriptions(String resource) {
        return new ModelAndViewBuilder().setViewName(resource).addObject("books", bookService.getBookDescriptions()).build();
    }

    @Override
    public ModelAndView getBookDetails(String resource, Long id) {
        BookDescription bookDescription = bookService.getBookDescription(id);
        Collection<Book> books = bookService.bookList();
        BookDetailsView view = new BookDetailsView(
                bookDescription,
                books.stream().filter(book -> book.getOnHolder().equals(1)).collect(Collectors.toMap(book -> book, book ->bookService.getLastMovement(book).getTo())),
                books.stream().filter(book -> book.getOnHolder().equals(0)).collect(Collectors.toMap(book -> book, book ->bookService.getLastMovement(book).getTo()))
        );
        return new ModelAndViewBuilder().setViewName(resource).addObject("bookdescription",view).build();
    }

    @Override
    public ModelAndView updateBookDetails(String resources, Long id, String name, String author, String isbn) {
        BookDescription bookDescription = bookService.getBookDescription(id);
        bookDescription.setName(name);
        bookDescription.setAuthor(author);
        bookDescription.setIsbn(isbn);
        bookService.updateBookDescription(bookDescription);
        return getBookDetails(resources, id);
    }

    @Override
    public ModelAndView removeBook(String resource, Long id) {
        bookService.remove(id);
        return getBookDetails(resource, id);
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
