package com.chan.ssb.player;

import com.chan.ssb.team.Team;

public class PlayerDTO {

    private long id;
    private String name;
    private long number;
    private Team team;

    public PlayerDTO() {
    }

    public PlayerDTO(long id, String name, long number, Team team) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.team = team;
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

    public Team getTeam() {
        return team;
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

    public void setTeam(Team team) {
        this.team = team;
    }

    public static PlayerDTO fromEntity(Player player) {
        return new PlayerDTO(player.getId(), player.getName(), player.getNumber(), player.getTeam());
    }
}
