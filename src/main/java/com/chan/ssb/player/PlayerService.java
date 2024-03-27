package com.chan.ssb.player;

import com.chan.ssb.exception.EntityNotFoundException;
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
        Player player = playerRepository.findById(id).orElse(null);
        if(player == null) {
            throw new EntityNotFoundException("Player not found with id: " + id);
        }
        return PlayerDTO.fromEntity(player);
    }

    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDTO> returnPlayers = players.stream().map(PlayerDTO::fromEntity).toList();
        if(returnPlayers.isEmpty()) {
            throw new EntityNotFoundException("No players found");
        }
        return returnPlayers;
    }

    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        TeamDTO teamDTO = teamService.getTeamById(playerDTO.getTeamId());
        if(teamDTO == null) {
            throw new EntityNotFoundException("Team not found with id: " + playerDTO.getTeamId());
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
        Team team = new Team(teamDTO.getId(), teamDTO.getName(), teamDTO.getCity(), teamDTO.getChampionships());
        getPlayerById(id);

        Player player = new Player(id, playerDTO.getName(), playerDTO.getNumber(), team);
        return PlayerDTO.fromEntity(playerRepository.save(player));
    }

    public void deletePlayer(long id) {
        PlayerDTO playerDTO = getPlayerById(id);
        playerRepository.deleteById(id);
    }

}
