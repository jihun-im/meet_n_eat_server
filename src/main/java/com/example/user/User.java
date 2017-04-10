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

//    @OneToMany(mappedBy = "user")
//    private Set<Request> requests = new HashSet<>();

    @Id
    private String id;

    public String password_hash;

    public String email;

    public String picture;

    public User(String id, String password_hash, String email, String picture) {
        this.id = id;
        this.password_hash = password_hash;
        this.email = email;
        this.picture = picture;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getId() {
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
