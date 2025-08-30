package com.santosh.SpringSecurity.Authentication.controller;

import com.santosh.SpringSecurity.Application.model.Student;
import com.santosh.SpringSecurity.Authentication.model.User;
import com.santosh.SpringSecurity.Authentication.service.UsersAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsersAuthService usersAuthService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {

        String token = usersAuthService.verify(user);

        if(token.equals("Failed")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        return ResponseEntity.ok(token);
    }
}
