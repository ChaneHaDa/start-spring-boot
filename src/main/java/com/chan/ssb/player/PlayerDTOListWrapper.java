package com.chan.ssb.player;

import java.util.List;

public class PlayerDTOListWrapper {
    private List<PlayerDTO> players;

    public PlayerDTOListWrapper() {
    }

    public PlayerDTOListWrapper(List<PlayerDTO> players) {
        this.players = players;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }
}
