package ru.sbrf.ku.library.services.impl;

import org.springframework.stereotype.Service;
import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.BookDescription;
import ru.sbrf.ku.library.services.BookService;

import java.util.Collection;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl( BookDao bookDao ) {
        this.bookDao = bookDao;
    }

    @Override
    public Collection<BookDescription> getBookDescriptions() {
        return bookDao.listOfBookDescriptions();
    }

    @Override
    public Book get( Long id ) {
        return bookDao.get( id );
    }

    @Override
    public void update( Book book ) {
        bookDao.update( book );
    }

    @Override
    public void updateBookDescription(BookDescription description) {
        bookDao.updateBookDescription(description);
    }

    @Override
    public void add( Book book ) {
        bookDao.add( book );
    }

    @Override
    public BookDescription getBookDescription(Long id) {
        return bookDao.getBookDescription(id);
    }
}
