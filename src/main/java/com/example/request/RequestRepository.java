package com.example.request;

import com.example.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by jihun.im on 2017-04-11.
 */
public interface RequestRepository extends JpaRepository<Request, Long>{
    Optional<Request> findById(long id);
}
