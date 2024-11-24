//package com.Academy.Task_Tool.Services;
//
//import com.Academy.Task_Tool.Entity.User;
//import com.Academy.Task_Tool.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.util.Optional;
//import java.util.Random;
//
//@Service
//public class OtpService {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private EmailService emailService;
//
//
//    public String generateOtp(){
//        Random random=new Random();
//        return String.format("%06d",random.nextInt(1000000));
//    }
//    // Create and send OTP
//    public void createAndSendOtp(String email) {
//        Optional<User> userOpt = userRepository.findByEmail(email);
//        if (userOpt.isEmpty()) {
//            throw new UsernameNotFoundException("User not found with email: " + email);
//        }
//
//        User user = userOpt.get();
//        String otp = generateOtp();
//        Instant expirationTime = Instant.now().plusSeconds(300); // 5 minutes
//
//        user.setOtp(otp);
//        user.setOtpExpirationTime(expirationTime);
//        userRepository.save(user);
//
//        // Send OTP via email
//        String subject = "Password Reset OTP";
//        String text = "Your OTP for password reset is: " + otp + ". It is valid for 5 minutes.";
//        emailService.sendSimpleMessage(email, subject, text);
//    }
//
//    // Validate OTP
//    public boolean validateOtp(String email, String otp) {
//        Optional<User> userOpt = userRepository.findByEmail(email);
//        if (userOpt.isEmpty()) {
//            return false;
//        }
//
//        User user = userOpt.get();
//        boolean isValid = user.isOtpValid(otp);
//        if (isValid) {
//            user.clearOtp();
//            userRepository.save(user);
//        }
//        return isValid;
//    }
//
//
//}
