package com.example.request;

import com.example.user.User;
import com.example.user.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jihun.im on 2017-04-10.
 */
@RestController
@RequestMapping("/api/v1/requests")
public class RequestRestController {

    Logger log = Logger.getLogger(RequestRestController.class.getName());

    RequestRepository requestRepository;

    @Autowired
    public RequestRestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Request> readRequests() {
        return this.requestRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Request readRequest(@PathVariable int id) {
        return this.requestRepository.findById((long)id).map(user->{
            return user;
        }).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Request newRequest) {
        return null;
    }

}
