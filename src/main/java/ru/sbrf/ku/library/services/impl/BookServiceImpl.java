package ru.sbrf.ku.library.services.impl;

import org.springframework.stereotype.Service;
import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.BookDescription;
import ru.sbrf.ku.library.services.BookService;

import java.util.Collection;
import java.util.HashSet;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl( BookDao bookDao ) {
        this.bookDao = bookDao;
    }

    @Override
    public Collection<BookDescription> getBookDescriptions() {
//        Set<BookDescription> bookSet = new HashSet<>(  );
//        for (Book book : bookDao.list()){
//            bookSet.add( BookDescription.build( book ) );
//        }
        return bookDao.listOfBookNames();

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
    public void add( Book book ) {
        bookDao.add( book );
    }
}
