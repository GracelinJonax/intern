package com.example.seatingarrangement.Service.Impl;

import com.example.seatingarrangement.Dto.SeatingDto;
import com.example.seatingarrangement.Dto.TeamDto;
import com.example.seatingarrangement.Service.SeatingService;
import com.example.seatingarrangement.model.DefaultLayout;
import com.example.seatingarrangement.model.Team;
import com.example.seatingarrangement.repository.DefaultLayoutRepository;
import com.example.seatingarrangement.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class SeatingServiceImpl implements SeatingService {
    static TreeSet<Integer> clusters = new TreeSet<>();
    static String arrangement[][];
    static int tempLayout[][];
    static int totalSeating[][];
    static boolean track[][];
    static int lastx = -1;
    static int lasty = -1;
    static int count = 0;
    static int minSteps = 100;
    static int[][] trace;
    private final DefaultLayoutRepository defaultLayoutRepository;
    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;

    public SeatingServiceImpl(DefaultLayoutRepository defaultLayoutRepository, ModelMapper modelMapper,
            TeamRepository teamRepository) {
        this.defaultLayoutRepository = defaultLayoutRepository;
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
    }

    @Override
    public int[][] getLayoutService() {
        return defaultLayoutRepository.findAll().get(0).getDefaultLayout();
    }

    @Override
    public DefaultLayout saveLayoutService(DefaultLayout defaultLayout) {
        int count=0;
        String layout[][]=new String[defaultLayout.getDefaultLayout().length][defaultLayout.getDefaultLayout()[0].length];
        for(int i=0;i<defaultLayout.getDefaultLayout().length;i++){
            for(int j=0;j<defaultLayout.getDefaultLayout()[0].length;j++){
//                layout[i][j]="";
                if(defaultLayout.getDefaultLayout()[i][j]==1)
                    count++;
        }}
        Arrays.stream(layout).forEach(a->Arrays.fill(a,""));
        defaultLayout.setTotalSpace(count);
        defaultLayout.setLayout(layout);
        return defaultLayoutRepository.save(defaultLayout);
    }

    @Override
    public SeatingDto createLayoutService(List<TeamDto> teamDtoList) {
        int wantedSpace=0;
        for(TeamDto teamDto:teamDtoList)
            wantedSpace+=teamDto.getTotalMembers();
        int totalSpace=defaultLayoutRepository.findAll().get(0).getTotalSpace();
        if(wantedSpace>totalSpace){
            System.out.println("no space");
        return new SeatingDto();}
        List<Team> teamList = teamRepository.findAll();

        int total = (int) teamRepository.count();
        for (TeamDto teamDto : teamDtoList) {
            Team team = new Team();
            modelMapper.map(teamDto, team);
            String teamCode = createTeamCode(++total);
            team.setTeamCode(teamCode);
            teamRepository.save(team);
            teamList.add(team);
        }
        teamList.sort(Comparator.comparing(Team::getTotalMembers).reversed());
        DefaultLayout defaultLayoutClass = defaultLayoutRepository.findAll().get(0);
        int defaultLayout[][] = defaultLayoutClass.getDefaultLayout();
        arrangement = new String[defaultLayout.length][defaultLayout[0].length];
        findArrangement(teamList);
        SeatingDto seatingDto = new SeatingDto();
        List<SeatingDto.Team> teams = teamList.stream().map(a -> modelMapper.map(a, SeatingDto.Team.class))
                .collect(Collectors.toList());
        seatingDto.setTeams(teams);
        seatingDto.setArrangement(arrangement);
        defaultLayoutClass.setLayout(arrangement);
        defaultLayoutRepository.save(defaultLayoutClass);
//        for(int i=0;i<d)
        for (int i = 0; i < arrangement.length; i++) {
            for (int j = 0; j < arrangement[0].length; j++)
                System.out.print(arrangement[i][j] + " ");
            System.out.println();
        }
        defaultLayoutClass.setTotalSpace(totalSpace-wantedSpace);
        defaultLayoutRepository.save(defaultLayoutClass);
        return seatingDto;
    }
    private void findArrangement(List<Team> teamList) {
        tempLayout = defaultLayoutRepository.findAll().get(0).getDefaultLayout();
        track = new boolean[tempLayout.length][tempLayout[0].length];
        totalSeating = findTotalSeating(tempLayout);
        for(int i=0;i<totalSeating.length;i++){
            for(int j=0;j<totalSeating[0].length;j++)
                System.out.print(totalSeating[i][j]+" ");
            System.out.println();
        }
        for (Team team : teamList) {
            lastx = -1;
            lasty = -1;
            int totalMembers = team.getTotalMembers();
            int total = totalMembers;
            int max = (int) clusters.toArray()[clusters.size() - 1];
            count = 0;
            int c = count;
            while (total > 0) {
                if (total > max) {
                    findStartSeating(max, team.getTeamCode());
                    total = totalMembers - count;
                }
                else
                {
                    findStartSeating(total, team.getTeamCode());
                    total = totalMembers - count;
                }
                if (count == c) {
                    System.out.println("no space extend the office");
                    break;
                }
                c = count;
            }
        }
    }

    private void findStartSeating(int totalMembers, String teamCode) {
        int wantedx = 0;
        int wantedy = 0;
        int minDis = 100;
        int total = totalMembers;
        if (!clusters.contains(totalMembers)) {
            for (Integer check : clusters) {
                if (check < totalMembers) {
                    total = check;
                }
                else
                    break;
            }
        }
        for (int i = 1; i <= tempLayout.length; i++) {
            int c = 0;
            for (int j = 1; j <= tempLayout[0].length; j++) {
                if (totalSeating[i][j] <= total && totalSeating[i][j] != 0) {
                    if (lasty == -1 && lastx == -1 && totalSeating[i][j] == total) {
                        c = 1;
                        wantedx = i;
                        wantedy = j;
                        break;
                    } else if (lasty != -1 && lastx != -1) {
                        int calculatedDis = findDistance(i, j, teamCode);
                        if (calculatedDis < minDis || (calculatedDis == minDis && (totalSeating[i][j] == total||totalSeating[wantedx][wantedy]<totalSeating[i][j]))) {
                            wantedx = i;
                            wantedy = j;
                            minDis = calculatedDis;
                        }
                    }
                }
            }
            if (lasty == -1 && lastx == -1 && c == 1)
                break;
        }

        lastx = wantedx;
        lasty = wantedy;
        if(totalMembers>totalSeating[wantedx][wantedy])
            totalMembers=totalSeating[wantedx][wantedy];
        markSeating(wantedx, wantedy, teamCode, totalMembers + count);totalSeating = findTotalSeating(tempLayout);
    }

    int findDistance(int x, int y, String teamCode) {
        minSteps=100;
        trace=new int[arrangement.length][arrangement[0].length];
        findSteps(lastx, lasty, x, y, 0, teamCode);
//        int finalx = Math.abs((lastx - x)) * Math.abs((lastx - x));
//        int finaly = Math.abs((lasty - y)) * Math.abs((lasty - y));
//        double distance = Math.sqrt((finalx + finaly));
//        return distance;
        return minSteps;
    }

    boolean findSteps(int x, int y, int resultx, int resulty, int steps, String teamCode) {
//        log.
        if (x == resultx && y == resulty) {
            steps+=1;
            if (steps < minSteps)
                minSteps = steps;
            return false;
        }
        if((lastx>resultx&&x>lastx)||(lastx<resultx&&x<lastx)||(lasty>resulty&&y>lasty)||(lasty<resulty&&y<lasty))
            return false;

        if(steps>minSteps)
            return false;
        if (x > 0 && y > 0 && x <= arrangement.length && y <= arrangement[0].length&&trace[x-1][y-1]==0) {
            trace[x-1][y-1]=1;
            if ((track[x - 1][y - 1] == true &&arrangement[x - 1][y - 1].contains(
                    teamCode)) || (track[x - 1][y - 1] == false && totalSeating[x][y] == 0))
                steps -= 1;
            if(tempLayout[x-1][y-1]==-1)
                steps+=2;

            if (findSteps(x , y+1, resultx, resulty, steps+1, teamCode))
                return true;
            if (findSteps(x-1, y, resultx, resulty, steps+1, teamCode))
                return true;
            if (findSteps(x+1, y , resultx, resulty, steps+1, teamCode))
                return true;
            if (findSteps(x, y-1, resultx, resulty, steps+1, teamCode))
                return true;




//            trace[x-1][y-1]=0;
//            if ((track[x - 1][y - 1] == true && !arrangement[x - 1][y - 1].contains(
//                    teamCode)) || (track[x - 1][y - 1] == false && totalSeating[x][y] != 0))
//                steps-=1;
            return false;
        }
//        steps -= 1;
        return false;
    }

    private boolean markSeating(int x, int y, String teamCode, int totalMembers) {
        if (count == totalMembers) {
            return true;
        }
        if (x > 0 && y > 0 && x <= arrangement.length && y <= arrangement[0].length && totalSeating[x][y] != 0 && track[x - 1][y - 1] == false && arrangement[x - 1][y - 1] == null) {
            tempLayout[x - 1][y - 1] = 0;
            track[x - 1][y - 1] = true;
            arrangement[x - 1][y - 1] = "" + teamCode + (++count);
            if (markSeating(x, y - 1, teamCode, totalMembers) == true)
                return true;
            if (markSeating(x - 1, y, teamCode, totalMembers) == true)
                return true;
            if (markSeating(x, y + 1, teamCode, totalMembers) == true)
                return true;
            if (markSeating(x + 1, y, teamCode, totalMembers) == true)
                return true;
            return false;
        }
        return false;
    }

    private int[][] findTotalSeating(int[][] tempLayout) {
        clusters.clear();
        int totalSeating[][] = new int[tempLayout.length + 1][tempLayout[0].length + 1];
        for (int i = 1; i <= tempLayout.length; i++) {
            for (int j = 1; j <= tempLayout[0].length; j++) {
                if (tempLayout[i - 1][j - 1] == 0||tempLayout[i-1][j-1]==-1)
                    totalSeating[i][j] = 0;
                else if (totalSeating[i][j-1]==0||totalSeating[i-1][j]==0)
                    totalSeating[i][j]=totalSeating[i][j-1]+totalSeating[i-1][j]+tempLayout[i-1][j-1];
                else
                    totalSeating[i][j] = totalSeating[i][j - 1] + totalSeating[i - 1][j] - totalSeating[i - 1][j - 1] + tempLayout[i - 1][j - 1];
                clusters.add(totalSeating[i][j]);
//                System.out.print(totalSeating[i][j]+" ");
            }
//            System.out.println();
        }
        return totalSeating;
    }

    private String createTeamCode(int total) {
        String alph[] = { "", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z" };
        String teamCode = "";
        if (total % 26 == 0)
            teamCode += alph[(total / 26) - 1] + "Z";
        else
            teamCode += alph[total / 26] + alph[total % 26];
        return teamCode;
    }
}
