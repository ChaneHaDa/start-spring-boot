package com.chan.ssb.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SiteUserDTO createSiteUser(SiteUserDTO siteUserDTO) {
        SiteUser siteUser = new SiteUser(siteUserDTO.getId(), siteUserDTO.getUsername(), passwordEncoder.encode(siteUserDTO.getPassword()), siteUserDTO.getRole());
        SiteUser savedUser;

        try {
            savedUser = userRepository.save(siteUser);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Username already exists");
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred while saving the user");
        }

        return SiteUserDTO.fromEntity(savedUser);
    }
}
