package com.exam.JWt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.exam.model.Users;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody AuthRequest request) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
    );

    //Users user = (Users) authentication.getPrincipal();


    String token = jwtUtil.generateToken(request.getUsername());

    // Extract role
    String role = authentication.getAuthorities()
                      .iterator()
                      .next()
                      .getAuthority();

    return ResponseEntity.ok(new AuthResponse(token, role));
}



}
