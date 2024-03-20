package com.chan.ssb.team.v2;

import com.chan.ssb.team.v1.Team;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2/team")
public class TeamV2Controller {

    TeamV2[] teamsV2 = {
            new TeamV2(1, "Team 1", "City 1", 5, new Sport("Basketball", "NBA")),
            new TeamV2(2, "Team 2", "City 2", 3, new Sport("Football", "NFL")),
            new TeamV2(3, "Team 3", "City 3", 7, new Sport("Baseball", "MLB"))
    };
    Team[] teamsV1 = {
            new Team(1, "Team 1", "City 1", 5),
            new Team(2, "Team 2", "City 2", 3),
            new Team(3, "Team 3", "City 3", 7)
    };

    @GetMapping(value = "", params = "version=1")
    public Team[] getALLTeamV1() {
        return teamsV1;
    }

    @GetMapping(value = "", params = "version=2")
    public TeamV2[] getAllTeamsV2() {
        return teamsV2;
    }

    @GetMapping(value = "/header", headers = "X-API-VERSION=1")
    public Team[] getALLTeamV1ByHeader() {
        return teamsV1;
    }

    @GetMapping(value = "/header", headers = "X-API-VERSION=2")
    public TeamV2[] getAllTeamsV2ByHeader() {
        return teamsV2;
    }

    @GetMapping(value = "/accept", produces = "application/vnd.company.app-v1+json")
    public Team[] getALLTeamV1ByAccept() {
        return teamsV1;
    }

    @GetMapping(value = "/accept", produces = "application/vnd.company.app-v2+json")
    public TeamV2[] getAllTeamsV2ByAccept() {
        return teamsV2;
    }

}
