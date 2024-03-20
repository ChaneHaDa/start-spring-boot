package com.chan.ssb.team.v1;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class TeamDTO {
    private long id;
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @NotEmpty(message = "City must not be empty")
    private String city;
    @Min(value = 0, message = "Championships must be a positive number")
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
