package com.chan.ssb;

import jakarta.persistence.*;

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

}
