package com.example.request;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jihun.im on 2017-04-10.
 */
@Entity
public class Request {
    @Id
    @GeneratedValue
    private Long id;
}
