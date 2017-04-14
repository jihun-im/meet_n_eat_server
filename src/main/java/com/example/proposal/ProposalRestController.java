package com.example.proposal;

import com.example.request.Request;
import com.example.request.RequestRepository;
import com.example.request.RequestRestController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jihun on 2017-04-14.
 */

@RestController
@RequestMapping("/api/v1/proposals")
public class ProposalRestController {
    Logger log = Logger.getLogger(RequestRestController.class.getName());
    ProposalRepository proposalRepository;
    RequestRepository requestRepository;

    @Autowired
    public ProposalRestController(ProposalRepository proposalRepository, RequestRepository requestRepository) {
        this.proposalRepository = proposalRepository;
        this.requestRepository = requestRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Proposal> readProposals() {
        return proposalRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Proposal readProposal(@PathVariable long id) {
        Proposal proposal = proposalRepository.findById(id);
        if ( proposal != null) {
            return proposal;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateProposal(@PathVariable long id, @RequestBody Proposal newProposal) {
        Proposal proposal = proposalRepository.findById(id);
        if ( proposal != null) {
            newProposal.setId(id);
            proposalRepository.save(newProposal);
            return new ResponseEntity<>("successfully updated\n" + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no such id in proposals\n" + id, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteProposal(@PathVariable long id) {
        Proposal proposal = proposalRepository.findById(id);
        if ( proposal != null) {
            proposalRepository.delete(proposal);
            return new ResponseEntity<>("successfully deleted\n" + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no such id in proposals\n" + id, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Proposal newProposal) {
//        if (newProposal.getRequest_id() == null) {
        log.info("request_id is null : " + newProposal.getRequest_id());
//            return new ResponseEntity<>("request_id is null\n" + newProposal, HttpStatus.EXPECTATION_FAILED);
//        }
        log.info("request_id is not null");
        Proposal proposal = new Proposal();
        proposal.setFilled(newProposal.getFilled());
        proposal.setRequest_id(newProposal.getRequest_id());
        proposal.setUser_proposed_from(-1);//TODO replace -1 to current logged in (maybe use token info)
        Request request = requestRepository.findById(newProposal.getRequest_id());
        if(request != null ) {
            proposal.setUser_proposed_to(request.getUser_id());
        } else {
            proposal.setUser_proposed_to(-1);
        }

        Proposal resultProposal = proposalRepository.save(proposal);
        if (resultProposal != null) {
            return new ResponseEntity<>("successfully added\n" + resultProposal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("error while saving to db\n" + resultProposal, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
