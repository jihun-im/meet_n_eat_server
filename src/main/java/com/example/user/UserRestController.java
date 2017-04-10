package com.example.user;

import com.example.UserAlreadyExistException;
import com.example.UserNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    User readUser(@PathVariable("id") String id) {
        return this.userRepository.findById(id).map(user -> {
            return user;
        })
                .orElseThrow(() -> new UserNotFoundException(id));
    }


    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody User newUser) {

        this.userRepository.findById(newUser.getId()).map(user -> {
            throw new UserAlreadyExistException(user.getId());
            //return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.ok().build());


        User user = this.userRepository.save(new User(newUser.getId(), newUser.getPassword_hash(), newUser.email, newUser.picture));
        if (user != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<?> update(@RequestBody User newUser) {
        return this.userRepository.findById(newUser.getId()).map(user -> {

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
        }).orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@RequestBody User newUser) {
        return this.userRepository.findById(newUser.getId()).map(user -> {
            this.userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.noContent().build());
    }

    private void validateId(String id) {
        this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
