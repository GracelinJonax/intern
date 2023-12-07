package com.example.seatingarrangement.Controller;

import com.example.seatingarrangement.Api.seatingArrangementApi;
import com.example.seatingarrangement.Dto.SeatingDto;
import com.example.seatingarrangement.Dto.TeamDto;
import com.example.seatingarrangement.Service.SeatingService;
import com.example.seatingarrangement.model.DefaultLayout;
import com.example.seatingarrangement.repository.DefaultLayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class apiController implements seatingArrangementApi {
    private final SeatingService seatingService;
    public apiController(SeatingService seatingService){
        this.seatingService=seatingService;
    }
    @Override
    public ResponseEntity<DefaultLayout> saveLayout(DefaultLayout layout) {
        return new ResponseEntity<>(seatingService.saveLayoutService(layout), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SeatingDto> createLayout(List<TeamDto> teamDtoList) {
       return new ResponseEntity<>(seatingService.createLayoutService(teamDtoList),HttpStatus.OK);
    }
}
