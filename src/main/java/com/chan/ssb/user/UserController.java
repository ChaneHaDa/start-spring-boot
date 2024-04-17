package com.chan.ssb.user;

import com.chan.ssb.authority.AuthorityDTO;
import com.chan.ssb.authority.AuthorityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final AuthorityService authorityService;

    public UserController(UserService userService, AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @PostMapping("")
    public SiteUserDTO createUser(SiteUserDTO userDTO) {
        SiteUserDTO createdUser = userService.createSiteUser(userDTO);
        if(createdUser != null) {
            AuthorityDTO authorityDTO = new AuthorityDTO(0, createdUser.getId(), "ROLE_USER");
            authorityService.createAuthority(authorityDTO);
            if(createdUser.getUsername().equals("admin")) {
                AuthorityDTO adminAuthorityDTO = new AuthorityDTO(0, createdUser.getId(), "ROLE_ADMIN");
                authorityService.createAuthority(adminAuthorityDTO);
            }
        }

        return createdUser;
    }
}
