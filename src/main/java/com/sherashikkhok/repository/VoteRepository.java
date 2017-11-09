package com.sherashikkhok.repository;

import com.sherashikkhok.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>  {

}
