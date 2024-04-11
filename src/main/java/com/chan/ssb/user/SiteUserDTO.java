package com.chan.ssb.user;

public class SiteUserDTO {

    private long id;
    private String username;
    private String password;
    private String role;

    public SiteUserDTO() {
    }

    public SiteUserDTO(long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
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

    public String getRole() {
        return role;
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

    public void setRole(String role) {
        this.role = role;
    }

    public static SiteUserDTO fromEntity(SiteUser user) {
        if(user == null) return null;
        return new SiteUserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
