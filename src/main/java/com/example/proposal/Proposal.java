package com.example.proposal;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Jihun on 2017-04-14.
 */

@Entity
@Data
@Getter
public class Proposal {

    @Autowired
    @Id
    @GeneratedValue
    private long id;

    @Autowired
    public long user_proposed_to;

    @Autowired
    public long user_proposed_from;

    @Autowired
    public long request_id;

    @Autowired
    public String filled;

    public Proposal(long user_proposed_to, long user_proposed_from, long request_id, String filled) {
        this.user_proposed_to = user_proposed_to;
        this.user_proposed_from = user_proposed_from;
        this.request_id = request_id;
        this.filled = filled;
    }

    Proposal() {
    }
}
