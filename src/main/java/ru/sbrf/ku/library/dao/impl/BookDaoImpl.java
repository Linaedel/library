package ru.sbrf.ku.library.dao.impl;

import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.Holder;
import ru.sbrf.ku.library.entities.Movement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookDaoImpl extends AbstractDaoImpl implements BookDao {

    @Override
    public void add(Book book) {
        em.getTransaction().begin();
        em.persist(book);
        System.out.println( ">>>>>>>>>>> created:" + book );
        System.out.println( ">>>>>>>>>>> before commit" );
        em.getTransaction().commit();
    }

    @Override
    public List<Book> list() {
        return em.createQuery("select b from Book b",Book.class).getResultList();    }

    @Override
    public Book get(Long id) {
        Book book = em.find(Book.class, id);
        return book.getDeleted() == 1? null : book;
    }

    @Override
    public void remove(Long id) {
        Book book = em.find(Book.class, id);
        if (book != null) {
            em.getTransaction().begin();
            book.setDeleted(1);
            em.getTransaction().commit();
        }
    }

    public void move(Book book, Holder holder) {
        Movement movement = new Movement();
        movement.setBook(book);
        movement.setFrom(
                getLastMovement(book.getMovements())==null?
                        null:
                        getLastMovement(book.getMovements()).getTo()
        );
        movement.setTo(holder);
        em.getTransaction().begin();
        if (book.getMovements() == null) {
            List<Movement> list = new ArrayList<>();
            list.add(movement);
            book.setMovements(list);
        } else {
            book.getMovements().add(movement);
        }
        em.getTransaction().commit();
    }

    public Movement getLastMovement(List<Movement> movements){
        long id = 0;
        Movement movement = null;
        if (movements != null) {
            for (Movement m : movements) {
                if (m.getId() > id) {
                    movement = m;
                }
            }
        }
        return movement;
    }


    @Override
    public List<Book> getBookOnHolder(Long holderId) {
        return list().stream()
                .filter(book -> (getLastMovement(book.getMovements()) != null &&
                        getLastMovement(book.getMovements()).getTo().getId() == holderId))
                .collect(Collectors.toList());
    }
}
