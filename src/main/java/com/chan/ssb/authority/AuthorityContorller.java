package com.chan.ssb.authority;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorityContorller {
    private final AuthorityService authorityService;

    public AuthorityContorller(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @PostMapping("/authority")
    public AuthorityDTO createAuthority(AuthorityDTO authorityDTO) {
        return authorityService.createAuthority(authorityDTO);
    }
}
