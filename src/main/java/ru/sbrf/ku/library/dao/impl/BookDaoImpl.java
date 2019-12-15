package ru.sbrf.ku.library.dao.impl;

import org.springframework.stereotype.Service;
import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.BookDescription;
import ru.sbrf.ku.library.entities.LibraryEntity;
import ru.sbrf.ku.library.entities.Movement;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookDaoImpl extends AbstractDaoImpl implements BookDao {

    @Override
    public List<Book> list() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public List<BookDescription> listOfBookNames() {
        return em.createQuery("select b from BookDescription b", BookDescription.class).getResultList();

//        return em.createQuery("select distinct (b.name) from Book b",String.class).getResultList();
    }

    @Override
    public Book get(Long id) {
        Book book = em.find(Book.class, id);
        return book.getDeleted() == 1 ? null : book;
    }

    @Override
    public void update(Book book) {
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
    }

    @Override
    public List<Book> getListByISBN(String isbn) {
        return em.createQuery("select b from Book b where b.isbn = ?1", Book.class).setParameter(1, isbn).getResultList();
    }

    @Override
    public String getISBNbyBookName(String name) {
        return em.createQuery("select b.isbn from Book b where b.name = ?1", String.class).setParameter(1, name).getSingleResult();
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

    @Override
    public Movement getLastMovement(Book book) {
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
            Book book = (Book) entity;
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<BookDescription> criteriaQuery = criteriaBuilder.createQuery(BookDescription.class);

            Root<BookDescription> descriptionRoot = criteriaQuery.from(BookDescription.class);
            criteriaQuery.select(descriptionRoot);
            List<Predicate> predicatesList = new ArrayList<>();
            predicatesList.add(criteriaBuilder.equal(descriptionRoot.get("author"), book.getDescription().getAuthor()));
            predicatesList.add(criteriaBuilder.equal(descriptionRoot.get("name"), book.getDescription().getName()));
            predicatesList.add(criteriaBuilder.equal(descriptionRoot.get("isbn"), book.getDescription().getIsbn()));

            criteriaQuery.where(criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()])));
            BookDescription presentResult = null;
            try {
                presentResult = em.createQuery(criteriaQuery).getSingleResult();
            } catch (NoResultException ignored) {
            }
            if (presentResult != null){
                book.setDescription(presentResult);
            }
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
    }
}
