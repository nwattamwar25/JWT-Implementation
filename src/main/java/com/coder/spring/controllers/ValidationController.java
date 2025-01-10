package com.coder.spring.controllers;

import com.coder.spring.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validate")
public class ValidationController {

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/token")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        boolean isValid = jwtUtils.validateJwtToken(token);
        if (isValid) {
            String username = jwtUtils.getUserNameFromJwtToken(token);
            return ResponseEntity.ok("Token is valid for user: " + username);
            //return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(401).body("Invalid token");
        }
    }
}