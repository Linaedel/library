package ru.sbrf.ku.library.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("person")
public class Person extends Holder{
    @Column(unique = true)
    private String username;

    private String password;

    @Column
    private String address;

    @Column(name = "phone")
    private String phone;

    @ManyToMany
    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set< Role > roles;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "person_req_b", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<BookDescription> requestedBooks;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public Person setAddress(String address ) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Person setPhone(String phone ) {
        this.phone = phone;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
    }

    public Set<BookDescription> getRequestedBooks() {
        return requestedBooks;
    }

    public void setRequestedBooks(Set<BookDescription> requestedBooks) {
        this.requestedBooks = requestedBooks;
    }
}
