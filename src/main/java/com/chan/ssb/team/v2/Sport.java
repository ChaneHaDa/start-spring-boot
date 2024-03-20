package com.chan.ssb.team.v2;

public class Sport {
    private String name;
    private String league;

    public Sport() {
    }

    public Sport(String name, String league) {
        this.name = name;
        this.league = league;
    }

    public String getName() {
        return name;
    }

    public String getLeague() {
        return league;
    }
}
