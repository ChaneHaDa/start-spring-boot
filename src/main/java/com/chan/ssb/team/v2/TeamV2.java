package com.chan.ssb.team.v2;

public class TeamV2 {
    private long id;
    private String name;
    private String city;
    private int championships;
    private Sport sport;

    public TeamV2() {
    }

    public TeamV2(long id, String name, String city, int championships, Sport sport) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.championships = championships;
        this.sport = sport;
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

    public Sport getSport() {
        return sport;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setChampionships(int championships) {
        this.championships = championships;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
