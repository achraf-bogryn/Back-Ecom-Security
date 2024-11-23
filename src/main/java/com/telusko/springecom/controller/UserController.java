package com.telusko.springecom.controller;

import com.telusko.springecom.model.User;
import com.telusko.springecom.service.JwtService;
import com.telusko.springecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("register")
    public void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PostMapping("login")
    public String login(@RequestBody User user) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                        user.getPassword()));

        if(authenticate.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        else
            return "Failed to login";
    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }
}
