package com.santosh.SpringSecurity.Authentication.service;

import com.santosh.SpringSecurity.Authentication.JwtServices.JwtService;
import com.santosh.SpringSecurity.Authentication.model.User;
import com.santosh.SpringSecurity.Authentication.model.UserPrincipal;
import com.santosh.SpringSecurity.Authentication.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersAuthService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authManager;



    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public User save(User user) {

        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );



        return userRepo.save(user);
    }

    public String verify(User user) {

        Authentication authentication
                = authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if(authentication.isAuthenticated()) {

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            long userId = userPrincipal.getId();
            String roleName = userPrincipal.getRole().name();

            return jwtService.generateToken(userId, roleName);
        }

        return "Failed";
    }

    public boolean existsByUsername(String username) {

        User user = userRepo.findByUsername(username);

        if(user == null) {
            return false;
        }

        return true;
    }

}