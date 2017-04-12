package com.example.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jihun.im on 2017-04-11.
 */
@RestController
@RequestMapping("/api/v1/{provider}")
public class UserRestLoginController {

    Logger log = Logger.getLogger(UserRestController.class.getName());

    UserRepository userRepository;


    @Autowired
    public UserRestLoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity<?> login(@RequestBody Integer newUser) {
        return null;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    ResponseEntity<?> logout(@RequestBody Integer newUser) {
        return null;
    }

}
