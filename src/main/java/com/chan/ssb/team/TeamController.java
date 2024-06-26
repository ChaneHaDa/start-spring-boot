package com.chan.ssb.team;

import com.chan.ssb.player.PlayerDTO;
import com.chan.ssb.player.PlayerDTOListWrapper;
import com.chan.ssb.player.PlayerService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("api/v1/team")
@RestController
//@CrossOrigin(origins="http://localhost:3000")
public class TeamController {

    // Team: id, name, city, championships

    private final TeamService teamService;
    private final PlayerService playerService;

    public TeamController(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping("")
    public CollectionModel<EntityModel<TeamDTO>> getAllTeams() {
        List<TeamDTO> teams = teamService.getAllTeams();

        List<EntityModel<TeamDTO>> teamEntityModels = teams.stream()
                .map(team -> EntityModel.of(team,
                        linkTo(methodOn(TeamController.class).getTeam(team.getId())).withSelfRel(),
                        linkTo(methodOn(TeamController.class).getAllTeams()).withRel("teams"),
                        linkTo(methodOn(TeamController.class).deleteTeam(team.getId())).withRel("delete"),
                        linkTo(methodOn(TeamController.class).updateTeam(team.getId(), team)).withRel("update"))
                )
                .toList();

        return CollectionModel.of(teamEntityModels,
                linkTo(methodOn(TeamController.class).getAllTeams()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<TeamDTO> getTeam(@PathVariable long id) {
        TeamDTO team = teamService.getTeamById(id);

        return EntityModel.of(team,
                linkTo(methodOn(TeamController.class).getTeam(id)).withSelfRel(),
                linkTo(methodOn(TeamController.class).getAllTeams()).withRel("teams"),
                linkTo(methodOn(TeamController.class).deleteTeam(id)).withRel("delete"),
                linkTo(methodOn(TeamController.class).updateTeam(id, team)).withRel("update")
        );
    }

    @PostMapping("")
    public ResponseEntity<CollectionModel<EntityModel<TeamDTO>>> createTeams(@Valid @RequestBody TeamDTOListWrapper teamDTOList) {
        List<TeamDTO> savedTeams = teamService.createTeams(teamDTOList.getTeamDTOList());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .build()
                .toUri();

        List<EntityModel<TeamDTO>> teamEntityModels = savedTeams.stream()
                .map(team -> EntityModel.of(team,
                        linkTo(methodOn(TeamController.class).getTeam(team.getId())).withSelfRel(),
                        linkTo(methodOn(TeamController.class).getAllTeams()).withRel("teams")))
                .toList();

        return ResponseEntity.created(location).body(CollectionModel.of(teamEntityModels,
                linkTo(methodOn(TeamController.class).getAllTeams()).withSelfRel()));
    }

    @PutMapping("/{id}")
    public EntityModel<TeamDTO> updateTeam(@PathVariable long id, TeamDTO team) {
        return EntityModel.of(teamService.updateTeam(id, team),
                linkTo(methodOn(TeamController.class).getTeam(id)).withSelfRel(),
                linkTo(methodOn(TeamController.class).getAllTeams()).withRel("teams"));
    }

    @DeleteMapping("/{id}")
    public EntityModel<TeamDTO> deleteTeam(@PathVariable long id) {
        teamService.deleteTeam(id);

        return EntityModel.of(null,
                linkTo(methodOn(TeamController.class).getAllTeams()).withRel("teams"));
    }

    @GetMapping("/{id}/players")
    public CollectionModel<EntityModel<PlayerDTO>> getPlayersByTeamId(@PathVariable long id) {
        List<PlayerDTO> players = teamService.getPlayersByTeamId(id);

        List<EntityModel<PlayerDTO>> playerEntityModels = players.stream()
                .map(player -> EntityModel.of(player,
                        linkTo(methodOn(TeamController.class).getPlayersByTeamId(id)).withSelfRel(),
                        linkTo(methodOn(TeamController.class).getTeam(id)).withRel("team")))
                .toList();

        return CollectionModel.of(playerEntityModels,
                linkTo(methodOn(TeamController.class).getPlayersByTeamId(id)).withSelfRel(),
                linkTo(methodOn(TeamController.class).getTeam(id)).withRel("team"));
    }

    @PostMapping("/{id}/players")
    public ResponseEntity<CollectionModel<EntityModel<PlayerDTO>>> createPlayers(@PathVariable long id, @Valid @RequestBody PlayerDTOListWrapper playerDTOList) {
        TeamDTO team = teamService.getTeamById(id);

        Team teamEntity = new Team(team.getId(), team.getName(), team.getCity(), team.getChampionships());

        List<PlayerDTO> savedPlayers = playerService.createPlayers(teamEntity, playerDTOList.getPlayers());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .build()
                .toUri();

        List<EntityModel<PlayerDTO>> playerEntityModels = savedPlayers.stream()
                .map(player -> EntityModel.of(player,
                        linkTo(methodOn(TeamController.class).getPlayersByTeamId(id)).withSelfRel(),
                        linkTo(methodOn(TeamController.class).getTeam(id)).withRel("team")))
                .toList();

        return ResponseEntity.created(location).body(CollectionModel.of(playerEntityModels,
                linkTo(methodOn(TeamController.class).getPlayersByTeamId(id)).withSelfRel(),
                linkTo(methodOn(TeamController.class).getTeam(id)).withRel("team")));
    }

}
