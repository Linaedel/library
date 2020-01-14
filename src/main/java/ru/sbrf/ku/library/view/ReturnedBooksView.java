package ru.sbrf.ku.library.view;

import ru.sbrf.ku.library.entities.Book;
import ru.sbrf.ku.library.entities.Placement;

import java.util.Collection;

public class ReturnedBooksView {
    private Integer hasOne;
    private Collection<Book> collection;
    private Collection<Placement> placements;

    public ReturnedBooksView(Integer hasOne, Collection<Book> collection, Collection<Placement> placements) {
        this.hasOne = hasOne;
        this.collection = collection;
        this.placements = placements;
    }

    public Integer getHasOne() {
        return hasOne;
    }

    public void setHasOne(Integer hasOne) {
        this.hasOne = hasOne;
    }

    public Collection<Book> getCollection() {
        return collection;
    }

    public void setCollection(Collection<Book> collection) {
        this.collection = collection;
    }

    public Collection<Placement> getPlacements() {
        return placements;
    }

    public void setPlacements(Collection<Placement> placements) {
        this.placements = placements;
    }
}
