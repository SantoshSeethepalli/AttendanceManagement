package com.santosh.SpringSecurity.Authentication.service;

import com.santosh.SpringSecurity.Authentication.model.UserPrincipal;
import com.santosh.SpringSecurity.Authentication.model.User;
import com.santosh.SpringSecurity.Authentication.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);

        if(user == null) {
            System.out.print("User not found!");

            throw new UsernameNotFoundException("User not found!");
        }
        return new UserPrincipal(user);
    }

    public UserDetails loadUserByUserId(int id) {

        User user = userRepo.findById(id)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with id: " + id)
                );

        return new UserPrincipal(user);
    }
}
