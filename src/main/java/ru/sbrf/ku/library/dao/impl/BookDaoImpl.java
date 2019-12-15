package ru.sbrf.ku.library.dao.impl;

import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.LibraryEntity;
import ru.sbrf.ku.library.entities.Movement;

import java.util.List;
import java.util.stream.Collectors;

public class BookDaoImpl extends AbstractDaoImpl implements BookDao {

    @Override
    public List<Book> list() {
        return em.createQuery("select b from Book b",Book.class).getResultList();    }

    @Override
    public List<String> listOfBookNames() {
        return em.createQuery("select distinct (b.name) from Book b",String.class).getResultList();    }


    @Override
    public Book get(Long id) {
        Book book = em.find(Book.class, id);
        return book.getDeleted() == 1? null : book;
    }

    @Override
    public void update(Book book) {
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
    }

    @Override
    public List<Book> getListByISBN(String isbn) {
        return em.createQuery("select b from Book b where b.isbn = ?1",Book.class).setParameter(1,isbn).getResultList();    }

    @Override
    public String getISBNbyBookName(String name) {
        return em.createQuery("select b.isbn from Book b where b.name = ?1",String.class).setParameter(1,name).getSingleResult();    }

    @Override
    public void remove(Long id) {
        Book book = em.find(Book.class, id);
        if (book != null) {
            em.getTransaction().begin();
            book.setDeleted(1);
            em.getTransaction().commit();
        }
    }

    @Override
    public Movement getLastMovement(Book book){
        long id = 0;
        Movement movement = null;
        List<Movement> movements;
        if ((movements = book.getMovements()) != null && movements.size() != 0) {
            for (Movement m : movements) {
                if (m.getId() > id) {
                    movement = m;
                }
            }
        }
        return movement;
    }


    @Override
    public List<Book> getBooksOnHolder(Long holderId) {
        return list().stream()
                .filter(book -> (getLastMovement(book) != null &&
                        getLastMovement(book).getTo().getId() == holderId))
                .collect(Collectors.toList());
    }

    @Override
    public void add(LibraryEntity entity) {
        if (entity instanceof Book) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
    }
}
