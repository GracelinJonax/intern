package com.example.seatingarrangement.Api;

import com.example.seatingarrangement.Dto.SeatingDto;
import com.example.seatingarrangement.Dto.TeamDto;
import com.example.seatingarrangement.model.DefaultLayout;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface seatingArrangementApi {
    @PostMapping("/defaultLayout")
    ResponseEntity<DefaultLayout> saveLayout(@RequestBody DefaultLayout layout);
    @PostMapping("/team")
    ResponseEntity<SeatingDto> createLayout(@RequestBody List<TeamDto> teamDtoList);
    @GetMapping("getDefaultLayout")
    ResponseEntity<int [][]> getLayout();
}
