package com.example.request;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by jihun.im on 2017-04-11.
 */
public interface RequestRepository extends JpaRepository<Request, Long>{
    //TODO add H2 db for data persistency
    Request findById(long id);
}
