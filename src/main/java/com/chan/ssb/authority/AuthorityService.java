package com.chan.ssb.authority;

import com.chan.ssb.user.SiteUser;
import com.chan.ssb.user.SiteUserDTO;
import com.chan.ssb.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final UserService userService;

    public AuthorityService(AuthorityRepository authorityRepository, UserService userService) {
        this.authorityRepository = authorityRepository;
        this.userService = userService;
    }

    public AuthorityDTO createAuthority(AuthorityDTO authorityDTO) {
        SiteUserDTO siteUserDTO = userService.getSiteUserById(authorityDTO.getSiteUserId());
        SiteUser siteUser = new SiteUser(siteUserDTO.getId(), siteUserDTO.getUsername(), siteUserDTO.getPassword());
        Authority authority = new Authority(authorityDTO.getId(), siteUser, authorityDTO.getName());
        Authority savedAuthority;

        try {
            savedAuthority = authorityRepository.save(authority);
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred while saving the authority");
        }

        return AuthorityDTO.fromEntity(savedAuthority);
    }

}
