package com.santosh.SpringSecurity.Authentication.service;

import com.santosh.SpringSecurity.Application.model.Role;
import com.santosh.SpringSecurity.Authentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import com.santosh.SpringSecurity.Authentication.service.UsersAuthService;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UsersAuthService userService;

    @Value("${ADMIN_USERNAME:admin}")
    private String adminUsername;

    @Value("${ADMIN_PASSWORD:admin123}")
    private String adminPassword;


    @Override
    public void run(String... args) {

        if (userService.existsByUsername("admin")) {
            return;
        }

        User admin = new User();

        admin.setUsername(adminUsername);
        admin.setPassword(adminPassword);
        admin.setRole(Role.ADMIN);

        userService.save(admin);
    }
}
