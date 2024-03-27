package com.chan.ssb.player;

import com.chan.ssb.team.Team;
import com.chan.ssb.team.TeamDTO;
import com.chan.ssb.team.TeamNotFoundException;
import com.chan.ssb.team.TeamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    public PlayerController(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping("{id}")
    public PlayerDTO getPlayerById(Long id) {
        return playerService.getPlayerById(id);
    }

    @PutMapping("{id}")
    public PlayerDTO updatePlayer(Long id, PlayerDTO playerDTO) {
        TeamDTO teamDTO = teamService.getTeamById(playerDTO.getTeamId());
        if(teamDTO == null) {
            throw new TeamNotFoundException("Team not found with id: " + playerDTO.getTeamId());
        }

        Team team = new Team(teamDTO.getId(), teamDTO.getName(), teamDTO.getCity(), teamDTO.getChampionships());
        playerDTO.setTeamId(team.getId());

        playerService.updatePlayer(id, playerDTO);

        PlayerDTO updatedPlayer = playerService.getPlayerById(id);

        return updatedPlayer;
    }

    @DeleteMapping("{id}")
    public void deletePlayer(Long id) {
        playerService.deletePlayer(id);
    }



}
