package com.exam.JWt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.exam.model.Users;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        logger.info("Login attempt for username: {}", request.getUsername());

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            logger.info("Authentication successful for username: {}", request.getUsername());

            String token = jwtUtil.generateToken(request.getUsername());

            String role = authentication.getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority();

            logger.info("JWT token generated for username: {}", request.getUsername());

            return ResponseEntity.ok(new AuthResponse(token, role));

        } catch (Exception e) {

            logger.error("Authentication failed for username: {}", request.getUsername());
            throw new RuntimeException("Invalid username or password");
        }
    }
}
