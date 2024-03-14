package com.chan.ssb;

public class TeamDTO {
    private long id;
    private String name;
    private String city;
    private int championships;

    public TeamDTO() {
    }

    public TeamDTO (long id, String name, String city, int championships) {
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

    public static TeamDTO fromEntity(Team team) {
        if(team == null) return null;
        return new TeamDTO(team.getId(), team.getName(), team.getCity(), team.getChampionships());
    }
}
