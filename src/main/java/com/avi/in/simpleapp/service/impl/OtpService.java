package com.avi.in.simpleapp.service.impl;

import com.avi.in.simpleapp.entity.Otp;
import com.avi.in.simpleapp.exception.GenericException;
import com.avi.in.simpleapp.repository.OtpRepository;
import com.avi.in.simpleapp.service.IEmailService;
import com.avi.in.simpleapp.service.IOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService implements IOtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private IEmailService iEmailService;

    private static final int OTP_VALID_DURATION = 5; // OTP valid duration in minutes
    private static final int OTP_LENGTH = 6;

    public String generateOtp(String email) {
        // Generate random OTP
        String otp = generateRandomDigits(OTP_LENGTH);
        // Store OTP in database or cache (e.g., Redis) with email as key
        // Example: otpRepository.save(email, otp);

        // send email
        String otpMessage = "Your one time otp is "+ otp;

        Otp otpEntity = new Otp();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setCreatedAt(LocalDateTime.now());
        Otp saveEntity = otpRepository.save(otpEntity);
        if(saveEntity!=null){
            String getId = iEmailService.sentMail(email,"One Time OTP",otpMessage);
            return otp;
        }else {
            throw new GenericException("email not sent");
        }
    }

    private String generateRandomDigits(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public boolean verifyOtp(String email, String otp) {
        // Retrieve OTP from database or cache
        // Example: String savedOtp = otpRepository.findByEmail(email);
        // Compare with provided OTP
        Optional<Otp> otpOptional = otpRepository.findByEmailAndOtp(email, otp);
        if (otpOptional.isPresent()) {
            Otp otpEntity = otpOptional.get();
            if (otpEntity.getCreatedAt().isAfter(LocalDateTime.now().minusMinutes(OTP_VALID_DURATION))) {
                return true;
            }
        }
        return false;
    }
}

