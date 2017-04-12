package com.example.request;

import com.example.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by jihun.im on 2017-04-10.
 */
@Entity
public class Request {

    @JsonIgnore
    @ManyToOne
    private User user;

    @Id
    @GeneratedValue
    private long id;

    //public long user_id;

    public String meal_type;

    public String location_string;

    public String latitude;

    public String meal_time;

    public String filled;


    public Request(User user, long user_id, String meal_type, String location_string, String latitude, String meal_time, String filled) {
        this.user = user;
        //this.user_id = user_id;
        this.meal_type = meal_type;
        this.location_string = location_string;
        this.latitude = latitude;
        this.meal_time = meal_time;
        this.filled = filled;
    }


    Request() {
    }

}
