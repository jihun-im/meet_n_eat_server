package com.example.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jihun.im on 2017-04-10.
 */
@Entity
@Data
@Getter
public class User {

    @Id
    @GeneratedValue
    @Autowired
    @JsonIgnore
    private long id;

    @Autowired
    public String userid;

    @Autowired
    public String password_hash;

    @Autowired
    public String email;

    @Autowired
    public String picture;

    public User(String userid, String password_hash, String email, String picture) {
        this.userid = userid;
        this.password_hash = password_hash;
        this.email = email;
        this.picture = picture;
    }


    User() {
    }

}
