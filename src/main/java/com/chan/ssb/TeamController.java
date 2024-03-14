package com.chan.ssb;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    // Team: id, name, city, championships
    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/team")
    public List<TeamDTO> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDTO> returnTeams = teams.stream().map(TeamDTO::fromEntity).toList();
        return returnTeams;
    }

    @GetMapping("/team/{id}")
    public TeamDTO getTeam(@PathVariable long id) {
        TeamDTO team = TeamDTO.fromEntity(teamRepository.findById(id).orElse(null));
        return team;
    }

    @PostMapping("/team")
    public List<TeamDTO> createTeams(@RequestBody List<TeamDTO> teamDTOList) {
        List<Team> newTeams = teamDTOList.stream().map(team -> new Team(team.getId(), team.getName(), team.getCity(), team.getChampionships())).toList();
        return teamRepository.saveAll(newTeams).stream().map(TeamDTO::fromEntity).toList();
    }

    @PutMapping("/team/{id}")
    public TeamDTO updateTeam(@PathVariable long id, TeamDTO team) {
        Team updateTeam = new Team(id, team.getName(), team.getCity(), team.getChampionships());
        return TeamDTO.fromEntity(teamRepository.save(updateTeam));
    }

    @DeleteMapping("/team/{id}")
    public void deleteTeam(@PathVariable long id) {
        teamRepository.deleteById(id);
    }

}
