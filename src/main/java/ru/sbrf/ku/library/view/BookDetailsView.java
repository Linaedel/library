package ru.sbrf.ku.library.view;

import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.BookDescription;
import ru.sbrf.ku.library.entities.Holder;

import java.util.Map;

public class BookDetailsView {
    private BookDescription details;
    private Map<Book, Holder> booksOnHolder;
    private Map<Book, Holder> booksOnHands;

    public BookDetailsView(BookDescription details, Map<Book, Holder> booksOnHolder, Map<Book, Holder> booksOnHands) {
        this.details = details;
        this.booksOnHolder = booksOnHolder;
        this.booksOnHands = booksOnHands;
    }

    public BookDescription getDetails() {
        return details;
    }

    public void setDetails(BookDescription details) {
        this.details = details;
    }

    public Map<Book, Holder> getBooksOnHolder() {
        return booksOnHolder;
    }

    public void setBooksOnHolder(Map<Book, Holder> booksOnHolder) {
        this.booksOnHolder = booksOnHolder;
    }

    public Map<Book, Holder> getBooksOnHands() {
        return booksOnHands;
    }

    public void setBooksOnHands(Map<Book, Holder> booksOnHands) {
        this.booksOnHands = booksOnHands;
    }
}
