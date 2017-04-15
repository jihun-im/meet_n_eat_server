package com.example.request;

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
        return requestRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Request readRequest(@PathVariable long id) {
        Request request = requestRepository.findById(id);
        if (request != null) {
            return request;
        } else {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    synchronized ResponseEntity<?> add(@RequestBody Request newRequest) {
        Request requestAlreadyInRepository = requestRepository.findById(newRequest.getId());
        if (requestAlreadyInRepository != null) {
            throw new RequestAlreadyExistException(newRequest.getId());
        }

        Request requestSavedInRepository = requestRepository.save(newRequest);
        return new ResponseEntity<>("successfully added\n" + requestSavedInRepository, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateResponse(@PathVariable long id, @RequestBody Request newRequest) {
        Request request = requestRepository.findById(id);
        if (request != null) {
            newRequest.setId(id);
            requestRepository.save(newRequest);
            return new ResponseEntity<>("successfully updated\n" + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no such id in proposals\n" + id, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteResponse(@PathVariable long id) {
        Request request = requestRepository.findById(id);
        if (request != null) {
            requestRepository.delete(request);
            return new ResponseEntity<>("successfully deleted\n" + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no such id in proposals\n" + id, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
