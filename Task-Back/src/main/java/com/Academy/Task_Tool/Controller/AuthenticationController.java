package com.Academy.Task_Tool.Controller;

import com.Academy.Task_Tool.DTO.*;
import com.Academy.Task_Tool.Services.AdminService;
import com.Academy.Task_Tool.Services.MyUserDetailsService;
//import com.Academy.Task_Tool.Services.OtpService;
import com.Academy.Task_Tool.jwt.JwtService;
import org.hibernate.dialect.PostgreSQLStructPGObjectJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;
//    @Autowired
//    private OtpService otpService;
    @Autowired
    private AdminService adminService;


    @PostMapping(value="/auth/login")
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
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            // Handle authentication failure
            LoginResponseDto response = new LoginResponseDto(null, "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON).body(response);
        }
    }

//    @PostMapping("/forgot-password")
//    public ResponseEntity<ApiResponseDto> requestOtp(@RequestBody ForgotPasswordRequestDto request) {
//        try {
//            otpService.createAndSendOtp(request.getEmail());
//            return ResponseEntity.ok(new ApiResponseDto("OTP has been sent to your email."));
//        } catch (UsernameNotFoundException ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto("User not found."));
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto("An error occurred."));
//        }
//    }
//
//    // Endpoint to reset password
//    @PostMapping("/reset-password")
//    public ResponseEntity<ApiResponseDto> resetPassword(@RequestBody ResetPasswordRequestDto request) {
//        try {
//            boolean isValid = otpService.validateOtp(request.getEmail(), request.getOtp());
//            if (!isValid) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponseDto("Invalid or expired OTP."));
//            }
//
//            adminService.updatePassword(request.getEmail(), request.getNewPassword());
//            return ResponseEntity.ok(new ApiResponseDto("Password has been reset successfully."));
//        } catch (UsernameNotFoundException ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto("User not found."));
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto("An error occurred."));
//        }
//    }
}
