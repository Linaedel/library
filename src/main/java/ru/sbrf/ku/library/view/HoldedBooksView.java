package ru.sbrf.ku.library.view;

import ru.sbrf.ku.library.entities.Book;

import java.util.Collection;

public class HoldedBooksView {
    Collection<Book> holdedBooks;

    public HoldedBooksView(Collection<Book> holdedBooks) {
        this.holdedBooks = holdedBooks;
    }

    public Collection<Book> getHoldedBooks() {
        return holdedBooks;
    }

    public void setHoldedBooks(Collection<Book> holdedBooks) {
        this.holdedBooks = holdedBooks;
    }
}
