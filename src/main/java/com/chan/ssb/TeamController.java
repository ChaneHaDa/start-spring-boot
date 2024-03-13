package com.chan.ssb;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TeamController {

    // Team: id, name, city, championships
    private final AtomicLong counter = new AtomicLong();
    private final List<Team> teams = new ArrayList<>();

    @GetMapping("/team")
    public List<Team> getAllTeams() {
        return teams;
    }

    @GetMapping("/team/{id}")
    public Team getTeam(@PathVariable long id) {
        for (Team team : teams) {
            if (team.id() == id) {
                return team;
            }
        }
        return null;
    }

    @PostMapping("/team")
    public Team createTeam(Team team) {
        Team newTeam = new Team(counter.incrementAndGet(), team.name(), team.city(), team.championships());
        teams.add(newTeam);
        return newTeam;
    }

    @PutMapping("/team/{id}")
    public Team updateTeam(@PathVariable long id, Team team) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).id() == id) {
                Team updatedTeam = new Team(id, team.name(), team.city(), team.championships());
                teams.set(i, updatedTeam);
                return team;
            }
        }
        return null;
    }

    @DeleteMapping("/team/{id}")
    public void deleteTeam(@PathVariable long id) {
        teams.removeIf(team -> team.id() == id);
    }
}
