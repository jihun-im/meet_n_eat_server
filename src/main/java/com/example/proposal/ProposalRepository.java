package com.example.proposal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Jihun on 2017-04-14.
 */

public interface ProposalRepository extends JpaRepository<Proposal, Long>{
    Proposal findById(long id);
}
