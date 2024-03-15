package com.chan.ssb.team;

import org.springframework.web.bind.annotation.*;

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
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public TeamDTO getTeam(@PathVariable long id) {
        return teamService.getTeamById(id);
    }

    @PostMapping("")
    public List<TeamDTO> createTeams(@RequestBody List<TeamDTO> teamDTOList) {
        return teamService.createTeams(teamDTOList);
    }

    @PutMapping("/{id}")
    public TeamDTO updateTeam(@PathVariable long id, TeamDTO team) {
        return teamService.updateTeam(id, team);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable long id) {
        teamService.deleteTeam(id);
    }

}
