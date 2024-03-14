package com.chan.ssb.team;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/team")
@RestController
public class TeamController {

    // Team: id, name, city, championships
    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("")
    public List<TeamDTO> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDTO> returnTeams = teams.stream().map(TeamDTO::fromEntity).toList();
        return returnTeams;
    }

    @GetMapping("/{id}")
    public TeamDTO getTeam(@PathVariable long id) {
        TeamDTO team = TeamDTO.fromEntity(teamRepository.findById(id).orElse(null));
        return team;
    }

    @PostMapping("")
    public List<TeamDTO> createTeams(@RequestBody List<TeamDTO> teamDTOList) {
        List<Team> newTeams = teamDTOList.stream().map(team -> new Team(team.getId(), team.getName(), team.getCity(), team.getChampionships())).toList();
        return teamRepository.saveAll(newTeams).stream().map(TeamDTO::fromEntity).toList();
    }

    @PutMapping("/{id}")
    public TeamDTO updateTeam(@PathVariable long id, TeamDTO team) {
        Team updateTeam = new Team(id, team.getName(), team.getCity(), team.getChampionships());
        return TeamDTO.fromEntity(teamRepository.save(updateTeam));
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable long id) {
        teamRepository.deleteById(id);
    }

}
