package com.chan.ssb.player;

import com.chan.ssb.team.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private long number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Team team;

    public Player() {
    }

    public Player(long id, String name, long number, Team team) {
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

}
