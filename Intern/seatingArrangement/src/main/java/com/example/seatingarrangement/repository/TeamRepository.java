package com.example.seatingarrangement.repository;

import com.example.seatingarrangement.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team,String> {
//    int CountOfTeam();
}
