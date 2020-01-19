package ru.sbrf.ku.library.entities;

import javax.persistence.*;

@Entity
@Table(name = "movement")
public class Movement implements LibraryEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Book book;

    @OneToOne(targetEntity = Holder.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Holder from;

    @OneToOne(targetEntity = Holder.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Holder to;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Holder getFrom() {
        return from;
    }

    public void setFrom(Holder from) {
        this.from = from;
    }

    public Holder getTo() {
        return to;
    }

    public void setTo(Holder to) {
        this.to = to;
    }

}
