package ru.sbrf.ku.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.BookDescription;
import ru.sbrf.ku.library.services.BookService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    @Autowired
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

    @Override
    public Map<BookDescription, Integer> getAvailabeBooks() {
        List<Book> availableBooks = bookDao.getBooksOnHolders();
        Map<BookDescription, Integer> result = new HashMap<>();
        for (Book b : availableBooks){
            BookDescription bd = b.getDescription();
            if (!result.containsKey(bd)){
                result.put(bd,0);
            } else {
                result.merge(bd,1, Integer::sum);
            }
        }
        return result;
    }
}
