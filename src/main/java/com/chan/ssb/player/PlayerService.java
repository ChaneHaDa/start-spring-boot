package com.chan.ssb.player;

import com.chan.ssb.team.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamService teamService;

    public PlayerService(PlayerRepository playerRepository, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
    }


    public PlayerDTO getPlayerById(long id) {
        return PlayerDTO.fromEntity(playerRepository.findById(id).orElse(null));
    }

    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDTO> returnPlayers = players.stream().map(PlayerDTO::fromEntity).toList();
        return returnPlayers;
    }

    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        TeamDTO teamDTO = teamService.getTeamById(playerDTO.getTeamId());
        if(teamDTO == null) {
            throw new TeamNotFoundException("Team not found with id: " + playerDTO.getTeamId());
        }
        Team team = new Team(teamDTO.getId(), teamDTO.getName(), teamDTO.getCity(), teamDTO.getChampionships());

        Player player = new Player(playerDTO.getId(), playerDTO.getName(), playerDTO.getNumber(), team);

        return PlayerDTO.fromEntity(playerRepository.save(player));
    }

    public List<PlayerDTO> createPlayers(Team team, List<PlayerDTO> playerDTOList) {
        List<Player> players = playerDTOList.stream().map(player -> new Player(player.getId(), player.getName(), player.getNumber(), team)).toList();
        return playerRepository.saveAll(players).stream().map(PlayerDTO::fromEntity).toList();
    }

    public PlayerDTO updatePlayer(long id, PlayerDTO playerDTO) {
        TeamDTO teamDTO = teamService.getTeamById(playerDTO.getTeamId());
        if(teamDTO == null) {
            throw new TeamNotFoundException("Team not found with id: " + playerDTO.getTeamId());
        }
        Team team = new Team(teamDTO.getId(), teamDTO.getName(), teamDTO.getCity(), teamDTO.getChampionships());
        Player player = new Player(id, playerDTO.getName(), playerDTO.getNumber(), team);
        return PlayerDTO.fromEntity(playerRepository.save(player));
    }

    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }

}
