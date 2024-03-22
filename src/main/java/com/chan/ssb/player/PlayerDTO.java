package com.chan.ssb.player;

public class PlayerDTO {

    private Long id;
    private String name;
    private long number;

    public PlayerDTO() {
    }

    public PlayerDTO(Long id, String name, long number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getNumber() {
        return number;
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

    public static PlayerDTO fromEntity(Player player) {
        return new PlayerDTO(player.getId(), player.getName(), player.getNumber());
    }
}
