package ru.sbrf.ku.library.services;

import org.springframework.web.servlet.ModelAndView;

public interface ViewService {
    ModelAndView getAvailableBooks(String resource);
    ModelAndView requestBook(String resource, Long id);
    ModelAndView getHoldedBooks(String resource);
    ModelAndView returnBook(String resource, Long id);
    ModelAndView getReturnedBooks(String resource);
    ModelAndView getBook(String resource, Long bookId, Long placementId);
    ModelAndView getRequestedBooks(String resource);
    ModelAndView giveBook(String resource, Long bookId, Long personId);
    ModelAndView getBookDescriptions(String resource);
    ModelAndView getBookDetails(String resource, Long id);
    ModelAndView updateBookDetails(String resources, Long id, String name, String author, String isbn);
    ModelAndView removeBook(String resource, Long id);
}
