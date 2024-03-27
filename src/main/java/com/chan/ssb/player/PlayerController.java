package com.chan.ssb.player;

import com.chan.ssb.team.Team;
import com.chan.ssb.team.TeamDTO;
import com.chan.ssb.team.TeamService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    public PlayerController(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping("")
    public CollectionModel<EntityModel<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> players = playerService.getAllPlayers();

        List<EntityModel<PlayerDTO>> playerEntityModels = players.stream()
                .map(player -> EntityModel.of(player,
                        linkTo(methodOn(PlayerController.class).getPlayerById(player.getId())).withSelfRel(),
                        linkTo(methodOn(PlayerController.class).updatePlayer(player.getId(), null)).withRel("update"),
                        linkTo(methodOn(PlayerController.class).deletePlayer(player.getId())).withRel("delete"))
                )
                .toList();

        return CollectionModel.of(playerEntityModels, linkTo(methodOn(PlayerController.class).getAllPlayers()).withSelfRel());
    }

    @GetMapping("{id}")
    public EntityModel<PlayerDTO> getPlayerById(@PathVariable long id) {
        return EntityModel.of(playerService.getPlayerById(id),
                linkTo(methodOn(PlayerController.class).getPlayerById(id)).withSelfRel(),
                linkTo(methodOn(PlayerController.class).updatePlayer(id, null)).withRel("update"),
                linkTo(methodOn(PlayerController.class).deletePlayer(id)).withRel("delete"),
                linkTo(methodOn(PlayerController.class).getAllPlayers()).withRel("players"));
    }

    @PutMapping("{id}")
    public EntityModel<PlayerDTO> updatePlayer(@PathVariable long id, PlayerDTO playerDTO) {
        TeamDTO teamDTO = teamService.getTeamById(playerDTO.getTeamId());
        Team team = new Team(teamDTO.getId(), teamDTO.getName(), teamDTO.getCity(), teamDTO.getChampionships());
        playerDTO.setTeamId(team.getId());

        PlayerDTO updatedPlayer = playerService.updatePlayer(id, playerDTO);

        return EntityModel.of(updatedPlayer,
                linkTo(methodOn(PlayerController.class).getPlayerById(id)).withSelfRel(),
                linkTo(methodOn(PlayerController.class).updatePlayer(id, null)).withRel("update"),
                linkTo(methodOn(PlayerController.class).deletePlayer(id)).withRel("delete"));
    }

    @DeleteMapping("{id}")
    public EntityModel<PlayerDTO> deletePlayer(@PathVariable long id) {
        playerService.deletePlayer(id);
        return EntityModel.of(null,
                linkTo(methodOn(PlayerController.class).deletePlayer(id)).withSelfRel(),
                linkTo(methodOn(PlayerController.class).getAllPlayers()).withRel("players"));
    }

}
