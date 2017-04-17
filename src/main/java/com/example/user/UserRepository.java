package com.example.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by jihun.im on 2017-04-10.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User> findById(String id);
    Optional<User> findById(long id);

    User findByUserid(String userid);
}
