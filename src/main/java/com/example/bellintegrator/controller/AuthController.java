package com.example.bellintegrator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthController {

    private Map<String, String> users = new HashMap<>();

    @RequestMapping("/auth")
    public ResponseEntity<String> authenticate(@RequestParam String username, @RequestParam String password) {
        users.put(username, password);
        return ResponseEntity.ok("User {} was added and authenticated".formatted(username));
    }

    @GetMapping("/check")
    public ResponseEntity<String> checkAuthentication(@RequestParam String username, @RequestParam String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            return ResponseEntity.ok("Authenticated");
        } else {
            return ResponseEntity.ok("Not Authenticated");
        }
    }
}
