package com.chan.ssb.player;

import com.chan.ssb.team.Team;
import com.chan.ssb.team.TeamDTO;
import com.chan.ssb.exception.EntityNotFoundException;
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
    public PlayerDTO getPlayerById(@PathVariable long id) {
        return playerService.getPlayerById(id);
    }

    @PutMapping("{id}")
    public PlayerDTO updatePlayer(@PathVariable long id, PlayerDTO playerDTO) {
        TeamDTO teamDTO = teamService.getTeamById(playerDTO.getTeamId());
        Team team = new Team(teamDTO.getId(), teamDTO.getName(), teamDTO.getCity(), teamDTO.getChampionships());
        playerDTO.setTeamId(team.getId());

        PlayerDTO updatedPlayer = playerService.updatePlayer(id, playerDTO);

        return updatedPlayer;
    }

    @DeleteMapping("{id}")
    public void deletePlayer(@PathVariable long id) {
        playerService.deletePlayer(id);
    }

}
