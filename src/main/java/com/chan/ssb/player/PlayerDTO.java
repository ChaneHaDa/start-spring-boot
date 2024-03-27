package com.chan.ssb.player;

import com.chan.ssb.team.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PlayerDTO {

    private long id;
    private String name;
    private long number;
    private long teamId;

    public PlayerDTO() {
    }

    public PlayerDTO(long id, String name, long number, long teamId) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.teamId = teamId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getNumber() {
        return number;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public static PlayerDTO fromEntity(Player player) {
        return new PlayerDTO(player.getId(), player.getName(), player.getNumber(), player.getTeam().getId());
    }
}
