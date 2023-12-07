package com.example.seatingarrangement.repository.Service.Impl;

import com.example.seatingarrangement.repository.TeamRepository;
import com.example.seatingarrangement.repository.Service.TeamRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class TeamRepositoryServiceImpl implements TeamRepositoryService {
    private final TeamRepository teamRepository;
    public TeamRepositoryServiceImpl(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }
}
