package com.example.seatingarrangement.Dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamDto {

    private List<Team> teamList;
    private boolean sort;
    @Data
    public static class Team{
    private String teamName;
    private int totalMembers;
}}
