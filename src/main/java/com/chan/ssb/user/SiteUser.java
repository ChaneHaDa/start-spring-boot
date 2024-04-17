package com.chan.ssb.user;

import com.chan.ssb.authority.Authority;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String username;

    private String password;

    @OneToMany(mappedBy="siteUser",fetch=FetchType.EAGER)
    private Set<Authority> authorities;

    public SiteUser() {
    }

    public SiteUser(long id, String username, String password) {
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

    public Set<Authority> getAuthorities() {
        return authorities;
    }

}
