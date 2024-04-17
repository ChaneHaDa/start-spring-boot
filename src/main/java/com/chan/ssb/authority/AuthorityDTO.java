package com.chan.ssb.authority;

public class AuthorityDTO {
    private long id;
    private long siteUserId;
    private String name;

    public AuthorityDTO() {
    }

    public AuthorityDTO(long id, long siteUserId, String name) {
        this.id = id;
        this.siteUserId = siteUserId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public long getSiteUserId() {
        return siteUserId;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSiteUserId(long siteUserId) {
        this.siteUserId = siteUserId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static AuthorityDTO fromEntity(Authority authority) {
        return new AuthorityDTO(authority.getId(), authority.getSiteUser().getId(), authority.getName());
    }
}
