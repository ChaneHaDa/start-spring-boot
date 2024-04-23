package com.chan.ssb.user;

import com.chan.ssb.authority.AuthorityDTO;
import com.chan.ssb.authority.AuthorityService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//
//    }
//
//    public SiteUserDTO createSiteUser(SiteUserDTO siteUserDTO) {
//        SiteUser siteUser = new SiteUser(siteUserDTO.getId(), siteUserDTO.getUsername(), passwordEncoder.encode(siteUserDTO.getPassword()));
//        SiteUser savedUser;
//
//        try {
//            savedUser = userRepository.save(siteUser);
//        } catch (DataIntegrityViolationException e) {
//            throw new IllegalArgumentException("Username already exists");
//        } catch (Exception e) {
//            throw new IllegalArgumentException("An error occurred while saving the user");
//        }
//
//        return SiteUserDTO.fromEntity(savedUser);
//    }
//
//    public SiteUserDTO getSiteUserById(long siteUserId) {
//        SiteUser siteUser = userRepository.findById(siteUserId).orElse(null);
//        if(siteUser == null) {
//            throw new IllegalArgumentException("User not found with id: " + siteUserId);
//        }
//        return SiteUserDTO.fromEntity(siteUser);
//    }
}
