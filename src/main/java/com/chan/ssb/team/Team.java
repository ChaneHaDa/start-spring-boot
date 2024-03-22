package com.chan.ssb.team;

import com.chan.ssb.player.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String city;
    @Column
    private int championships;

    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private List<Player> players;

    public Team() {
    }

    public Team(long id, String name, String city, int championships) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.championships = championships;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getChampionships() {
        return championships;
    }

    public List<Player> getPlayers() {
        return players;
    }

}
