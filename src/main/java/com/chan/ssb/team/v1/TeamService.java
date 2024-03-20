package com.chan.ssb.team.v1;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamDTO getTeamById(long id) {
        return TeamDTO.fromEntity(teamRepository.findById(id).orElse(null));
    }

    public List<TeamDTO> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDTO> returnTeams = teams.stream().map(TeamDTO::fromEntity).toList();
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
        Team updateTeam = new Team(id, team.getName(), team.getCity(), team.getChampionships());
        return TeamDTO.fromEntity(teamRepository.save(updateTeam));
    }

    public void deleteTeam(long id) {
        teamRepository.deleteById(id);
    }


}
