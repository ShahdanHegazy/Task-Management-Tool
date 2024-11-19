package com.Academy.Task_Tool.Controller;

import com.Academy.Task_Tool.DTO.LoginRequestDto;
import com.Academy.Task_Tool.DTO.LoginResponseDto;
import com.Academy.Task_Tool.Services.MyUserDetailsService;
import com.Academy.Task_Tool.jwt.JwtService;
import org.hibernate.dialect.PostgreSQLStructPGObjectJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            // Authenticate user using email and password
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

            authenticationManager.authenticate(authenticationToken);

            // Load user details
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginRequest.getEmail());

            // Generate JWT token
            String token = jwtService.generateToken(userDetails.getUsername());

            // Create response
            LoginResponseDto response = new LoginResponseDto(token, "Login successful");
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            // Handle authentication failure
            LoginResponseDto response = new LoginResponseDto(null, "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

    }
}
