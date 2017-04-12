package com.example.user;

import com.example.request.Request;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jihun.im on 2017-04-10.
 */
@Entity
public class User {

    @OneToMany(mappedBy = "user")
    private Set<Request> requests = new HashSet<>();

    @Id
    @GeneratedValue
    private long id;

    public String password_hash;

    public String email;

    public String picture;

    public User(String password_hash, String email, String picture) {
        this.password_hash = password_hash;
        this.email = email;
        this.picture = picture;
    }

    public Set<Request> getRequests() {
        return this.requests;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getId() {
        return this.id;
    }

    public String getPassword_hash() {
        return this.password_hash;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPicture() {
        return this.picture;
    }

    User() {
    }

}
