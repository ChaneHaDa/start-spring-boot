package com.chan.ssb.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SiteUserDTO {

    private long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public SiteUserDTO() {
    }

    public SiteUserDTO(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setId(long id){
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static SiteUserDTO fromEntity(SiteUser user) {
        if(user == null) return null;
        return new SiteUserDTO(user.getId(), user.getUsername(), user.getPassword());
    }
}
