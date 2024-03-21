package com.chan.ssb.team;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("api/v1/team")
@RestController
public class TeamController {

    // Team: id, name, city, championships

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @GetMapping("")
    public MappingJacksonValue getAllTeams() {
        List<TeamDTO> teams = teamService.getAllTeams();
        if(teams.isEmpty()) {
            throw new TeamNotFoundException("No teams found");
        }

        List<EntityModel<TeamDTO>> teamEntityModels = teams.stream()
                .map(team -> EntityModel.of(team,
                        linkTo(methodOn(TeamController.class).getTeam(team.getId())).withSelfRel(),
                        linkTo(methodOn(TeamController.class).getAllTeams()).withRel("teams"),
                        linkTo(methodOn(TeamController.class).deleteTeam(team.getId())).withRel("delete"),
                        linkTo(methodOn(TeamController.class).updateTeam(team.getId(), team)).withRel("update"))
                )
                .toList();

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(CollectionModel.of(teamEntityModels,
                linkTo(methodOn(TeamController.class).getAllTeams()).withSelfRel()));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "city");
        FilterProvider filters = new SimpleFilterProvider().addFilter("TeamFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("/{id}")
    public MappingJacksonValue getTeam(@PathVariable long id) {
        TeamDTO team = teamService.getTeamById(id);
        if(team == null) {
            throw new TeamNotFoundException("Team not found with id: " + id);
        }

        EntityModel<TeamDTO> teamEntityModel = EntityModel.of(team,
                linkTo(methodOn(TeamController.class).getTeam(id)).withSelfRel(),
                linkTo(methodOn(TeamController.class).getAllTeams()).withRel("teams"),
                linkTo(methodOn(TeamController.class).deleteTeam(id)).withRel("delete"),
                linkTo(methodOn(TeamController.class).updateTeam(id, team)).withRel("update"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(teamEntityModel);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "city");
        FilterProvider filters = new SimpleFilterProvider().addFilter("TeamFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
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
        TeamDTO teamDTO = teamService.getTeamById(id);
        if(teamDTO == null) {
            throw new TeamNotFoundException("Team not found with id: " + id);
        }
        return EntityModel.of(teamService.updateTeam(id, team),
                linkTo(methodOn(TeamController.class).getTeam(id)).withSelfRel(),
                linkTo(methodOn(TeamController.class).getAllTeams()).withRel("teams"));
    }

    @DeleteMapping("/{id}")
    public EntityModel<TeamDTO> deleteTeam(@PathVariable long id) {
        TeamDTO teamDTO = teamService.getTeamById(id);
        if(teamDTO == null) {
            throw new TeamNotFoundException("Team not found with id: " + id);
        }
        teamService.deleteTeam(id);

        return EntityModel.of(teamDTO,
                linkTo(methodOn(TeamController.class).getAllTeams()).withRel("teams"));
    }

}
