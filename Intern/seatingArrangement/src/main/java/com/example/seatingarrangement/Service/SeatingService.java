package com.example.seatingarrangement.Service;

import com.example.seatingarrangement.Dto.SeatingDto;
import com.example.seatingarrangement.Dto.TeamDto;
import com.example.seatingarrangement.model.DefaultLayout;
import org.springframework.stereotype.Service;

@Service
public interface SeatingService {
    DefaultLayout saveLayoutService(DefaultLayout defaultLayout);

    SeatingDto createLayoutService(TeamDto teamDtoList);

    int[][] getLayoutService();
}
