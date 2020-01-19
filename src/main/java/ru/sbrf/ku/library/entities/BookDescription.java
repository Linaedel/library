package ru.sbrf.ku.library.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bookdescription")
public class BookDescription {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column
    private String name;

    @Column
    private String author;

    @Column
    private Integer available;

    @Column
    private Integer requested;

    @OneToMany(mappedBy = "description")
    private List<Book> books;

    @ManyToMany(mappedBy = "requestedBooks")
    private Set<Person> requesters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getRequested() {
        return requested;
    }

    public void setRequested(Integer requested) {
        this.requested = requested;
    }

    public Set<Person> getRequesters() {
        return requesters;
    }

    public void setRequesters(Set<Person> requesters) {
        this.requesters = requesters;
    }
}
