package ru.sbrf.ku.library.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.dao.HolderType;
import ru.sbrf.ku.library.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookDaoImpl implements BookDao {
    private final EntityManager em;

    @Autowired
    public BookDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Book> list() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public List<BookDescription> listOfBookDescriptions() {
        return em.createQuery("select b from BookDescription b", BookDescription.class).getResultList();
    }

    @Override
    public Book get(Long id) {
        Book book = em.find(Book.class, id);
        return book.getDeleted() == 1 ? null : book;
    }

    @Override
    public BookDescription getBookDescription(Long id) {
        return em.find(BookDescription.class, id);
    }

    @Override
    @Transactional
    public void update(Book book) {
        em.merge(book);
    }

    @Override
    @Transactional
    public void updateBookDescription(BookDescription description) {
        em.merge(description);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Book book = em.find(Book.class, id);
        if (book != null) {
            book.setDeleted(1);
            em.merge(book);
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

    //TODO Переписать в качестве запроса
    @Override
    public List<Book> getBooksOnHolder(Holder holder) {
        List<Book> holderList = holder.getType().equals(HolderType.PLACEMENT.ordinal())? getBooksOnHolders(): getBooksOnHands();
        return holderList.stream()
                .filter(book -> (getLastMovement(book) != null &&
                        getLastMovement(book).getTo().getId() == holder.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksOnHolders(){
        return em.createQuery("select b from Book b where b.onHolder = 1 and b.deleted = 0", Book.class).getResultList();
    }

    @Override
    public List<Book> getBooksOnHands(){
        return em.createQuery("select b from Book b where b.onHolder = 0 and b.deleted = 0", Book.class).getResultList();
    }

    @Override
    public List<BookDescription> listOfAvailableBooks() {
        return em.createQuery("select b from BookDescription b where b.available > 0", BookDescription.class).getResultList();
    }

    private List<Book> getBooksOnHolders(List<Long> holderIds){
        return list().stream()
                .filter(book -> (getLastMovement(book) == null ||
                        holderIds.contains(getLastMovement(book).getTo().getId())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
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
            em.persist(entity);
        }
    }

    @Override
    @Transactional
    public void requestBook(Long id, Person person) {
        BookDescription requestedBook = em.find(BookDescription.class, id);
        requestedBook.getRequesters().add(person);
        requestedBook.setRequested(1);
        person.getRequestedBooks().add(requestedBook);
        em.merge(requestedBook);
        em.merge(person);
    }
}
