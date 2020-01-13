package ru.sbrf.ku.library.view;

import ru.sbrf.ku.library.entities.BookDescription;
import ru.sbrf.ku.library.entities.Person;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AvailableBooksView {
    private Collection<BookDescription> bookDescription;
    private Person currentUser;
    private Map<BookDescription, Integer> viewMap;

    public AvailableBooksView(Collection<BookDescription> bookDescription, Person currentUser) {
        this.bookDescription = bookDescription;
        this.currentUser = currentUser;
    }

    public Collection<BookDescription> getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(Collection<BookDescription> bookDescription) {
        this.bookDescription = bookDescription;
    }

    public Person getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }

    public Map<BookDescription, Integer> getViewMap(){
        Map<BookDescription, Integer> result = new HashMap<>();
        for (BookDescription bd : this.bookDescription){
                result.put(bd,bd.getRequesters().contains(this.currentUser)?1:0);
            }
        return result;
        }

    public void setViewMap(Map<BookDescription, Integer> viewMap) {
        this.viewMap = viewMap;
    }


}
