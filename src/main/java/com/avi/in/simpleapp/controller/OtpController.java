package com.avi.in.simpleapp.controller;

import com.avi.in.simpleapp.service.IOtpService;
import com.avi.in.simpleapp.service.impl.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {

    @Qualifier("otpService")
    @Autowired
    private IOtpService iOtpService;

    @PostMapping("/api/request-otp")
    public ResponseEntity<String> requestOtp(@RequestParam String email) {
        // Generate and send OTP to the provided email
        String otp = iOtpService.generateOtp(email);
        // Send OTP via email or SMS
        // Example: emailService.sendEmail(email, "Your OTP is: " + otp);
        return ResponseEntity.ok("OTP sent to " + email);
    }
    @PostMapping("/api/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        // Verify OTP
        boolean isValid = iOtpService.verifyOtp(email, otp);
        if (isValid) {
            // OTP is valid, proceed with user registration
            return ResponseEntity.ok("OTP is verified for this emailId"+ email);
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
    }
}

