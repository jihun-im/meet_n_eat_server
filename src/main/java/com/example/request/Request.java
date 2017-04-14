package com.example.request;

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
public class Request {

    @Autowired
    @Id
    @GeneratedValue
    private long id;

    @Autowired
    public long user_id;

    @Autowired
    public String meal_type;

    @Autowired
    public String location_string;

    @Autowired
    public String latitude;

    @Autowired
    public String longitude;

    @Autowired
    public String meal_time;

    @Autowired
    public String filled;


    public Request(long user_id, String meal_type, String location_string, String latitude, String longitude, String meal_time, String filled) {
        this.user_id = user_id;
        this.meal_type = meal_type;
        this.location_string = location_string;
        this.latitude = latitude;
        this.longitude = longitude;
        this.meal_time = meal_time;
        this.filled = filled;
    }

    Request() {
    }

}
