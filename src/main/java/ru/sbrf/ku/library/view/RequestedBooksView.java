package ru.sbrf.ku.library.view;

import ru.sbrf.ku.library.entities.BookDescription;

import java.util.Collection;

public class RequestedBooksView {
    private Integer hasOne;
    private Collection<BookDescription> collection;

    public RequestedBooksView(Collection<BookDescription> collection) {
        this.hasOne = collection.size();
        this.collection = collection;
    }

    public Integer getHasOne() {
        return hasOne;
    }

    public void setHasOne(Integer hasOne) {
        this.hasOne = hasOne;
    }

    public Collection<BookDescription> getCollection() {
        return collection;
    }

    public void setCollection(Collection<BookDescription> collection) {
        this.collection = collection;
    }
}
