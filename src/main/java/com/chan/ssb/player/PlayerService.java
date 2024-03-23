package com.chan.ssb.player;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
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
        Player player = new Player(playerDTO.getId(), playerDTO.getName(), playerDTO.getNumber(), playerDTO.getTeam());

        return PlayerDTO.fromEntity(playerRepository.save(player));
    }

    public List<PlayerDTO> createPlayers(List<PlayerDTO> playerDTOList) {
        List<Player> players = playerDTOList.stream().map(player -> new Player(player.getId(), player.getName(), player.getNumber(), player.getTeam())).toList();
        return playerRepository.saveAll(players).stream().map(PlayerDTO::fromEntity).toList();
    }

    public PlayerDTO updatePlayer(long id, PlayerDTO playerDTO) {
        Player player = new Player(id, playerDTO.getName(), playerDTO.getNumber(), playerDTO.getTeam());
        return PlayerDTO.fromEntity(playerRepository.save(player));
    }

    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }

}
