package com.chan.ssb.authority;

import com.chan.ssb.user.SiteUser;
import jakarta.persistence.*;

@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "site_user_id")
    private SiteUser siteUser;

    private String name;

    public Authority() {
    }

    public Authority(long id, SiteUser siteUser, String authority) {
        this.id = id;
        this.siteUser = siteUser;
        this.name = authority;
    }

    public long getId() {
        return id;
    }

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public String getName() {
        return name;
    }

}
