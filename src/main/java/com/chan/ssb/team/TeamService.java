package com.chan.ssb.team;

import com.chan.ssb.exception.EntityNotFoundException;
import com.chan.ssb.player.PlayerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamDTO getTeamById(long id) {
        TeamDTO teamDTO = TeamDTO.fromEntity(teamRepository.findById(id).orElse(null));
        if(teamDTO == null) {
            throw new EntityNotFoundException("Team not found with id: " + id);
        }
        return teamDTO;
    }

    public List<TeamDTO> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDTO> returnTeams = teams.stream().map(TeamDTO::fromEntity).toList();
        if(returnTeams.isEmpty()) {
            throw new EntityNotFoundException("No teams found");
        }
        return returnTeams;
    }

    public TeamDTO createTeam(TeamDTO teamDTO) {
        Team newTeam = new Team(0, teamDTO.getName(), teamDTO.getCity(), teamDTO.getChampionships());
        return TeamDTO.fromEntity(teamRepository.save(newTeam));
    }

    public List<TeamDTO> createTeams(List<TeamDTO> teamDTOList) {
        List<Team> newTeams = teamDTOList.stream().map(team -> new Team(team.getId(), team.getName(), team.getCity(), team.getChampionships())).toList();
        return teamRepository.saveAll(newTeams).stream().map(TeamDTO::fromEntity).toList();
    }

    public TeamDTO updateTeam(long id, TeamDTO team) {
        TeamDTO teamDTO = getTeamById(id);
        Team updateTeam = new Team(id, team.getName(), team.getCity(), team.getChampionships());
        return TeamDTO.fromEntity(teamRepository.save(updateTeam));
    }

    public void deleteTeam(long id) {
        TeamDTO teamDTO = getTeamById(id);
        teamRepository.deleteById(id);
    }

    public List<PlayerDTO> getPlayersByTeamId(long id) {
        Team team = teamRepository.findById(id).orElse(null);
        if(team == null) {
            throw new EntityNotFoundException("Team not found with id: " + id);
        }
        return team.getPlayers().stream().map(PlayerDTO::fromEntity).toList();
    }

}
