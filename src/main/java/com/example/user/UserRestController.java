package com.example.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jihun.im on 2017-04-10.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    Logger log = Logger.getLogger(UserRestController.class.getName());

    UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<User> readUsers() {
        return this.userRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User readUser(@PathVariable int id) {
        return this.userRepository.findById((long) id).map(user -> {
            return user;
        }).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    synchronized ResponseEntity<?> add(@RequestBody User newUser) {

        userRepository.findById(newUser.getId()).map(user -> {
            throw new UserAlreadyExistException("" + newUser.getId());
        }).orElse(null);

        User userInDb = userRepository.findByUserid(newUser.getUserid());
        if (userInDb != null) {
            // already exist!
            throw new UserAlreadyExistException("" + newUser.getUserid());
        }


        log.trace("newUser.getId() : " + newUser.getId());

        User user = this.userRepository.save(new User(newUser.getUserid(), newUser.getPassword_hash(), newUser.email, newUser.picture));
        if (user != null) {
            return new ResponseEntity<>("successfully added\n" + user, HttpStatus.OK);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<?> update(@RequestBody User newUser) {
        return this.userRepository.findById(newUser.getId()).map(user -> {
//TODO id로 PUT하는것을 user_id로 PUT하도록 바꿔야함
            String email = newUser.getEmail();
            if (email != null) {
                user.setEmail(email);
            }
            String password_hash = newUser.getPassword_hash();
            if (password_hash != null) {
                user.setPassword_hash(password_hash);
            }
            String picture = newUser.getPicture();
            if (picture != null) {
                user.setPicture(picture);
            }

            this.userRepository.save(user);
            return ResponseEntity.ok().build();
        }).orElse(new ResponseEntity<>("No such user", HttpStatus.EXPECTATION_FAILED));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@RequestBody User newUser) {
        return this.userRepository.findById(newUser.getId()).map(user -> {
            this.userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElse(new ResponseEntity<>("No such user", HttpStatus.EXPECTATION_FAILED));
    }
}
