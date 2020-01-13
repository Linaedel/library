package ru.sbrf.ku.library.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
public class Book implements LibraryEntity{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(targetEntity = BookDescription.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BookDescription description;

    @Column
    private Integer deleted;

    @Column
    private Integer onHolder;

    @OneToMany(targetEntity = Movement.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movement> movements;

    public Long getId() {
        return id;
    }

    public Book setId( Long id ) {
        this.id = id;
        return this;
    }

    public BookDescription getDescription() {
        return description;
    }

    public void setDescription(BookDescription description) {
        this.description = description;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public Book setMovements( List<Movement> movements ) {
        this.movements = movements;
        return this;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getOnHolder() {
        return onHolder;
    }

    public void setOnHolder(Integer onHolder) {
        this.onHolder = onHolder;
    }
}
