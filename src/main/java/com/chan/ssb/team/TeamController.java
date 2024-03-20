package com.chan.ssb.team;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/team")
@RestController
public class TeamController {

    // Team: id, name, city, championships

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @GetMapping("")
    public List<TeamDTO> getAllTeams() {
        List<TeamDTO> teams = teamService.getAllTeams();
        if(teams.isEmpty()) {
            throw new TeamNotFoundException("No teams found");
        }

        return teams;
    }

    @GetMapping("/{id}")
    public TeamDTO getTeam(@PathVariable long id) {
        TeamDTO team = teamService.getTeamById(id);
        if(team == null) {
            throw new TeamNotFoundException("Team not found with id: " + id);
        }

        return team;
    }

    @PostMapping("")
    public ResponseEntity<List<TeamDTO>> createTeams(@Valid @RequestBody TeamDTOListWrapper teamDTOList) {
        teamService.createTeams(teamDTOList.getTeamDTOList());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .build()
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public TeamDTO updateTeam(@PathVariable long id, TeamDTO team) {
        TeamDTO teamDTO = teamService.getTeamById(id);
        if(teamDTO == null) {
            throw new TeamNotFoundException("Team not found with id: " + id);
        }
        return teamService.updateTeam(id, team);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable long id) {
        TeamDTO teamDTO = teamService.getTeamById(id);
        if(teamDTO == null) {
            throw new TeamNotFoundException("Team not found with id: " + id);
        }
        teamService.deleteTeam(id);
    }

}
