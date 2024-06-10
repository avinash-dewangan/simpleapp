package com.avi.in.simpleapp.service.impl;

import com.avi.in.simpleapp.entity.Otp;
import com.avi.in.simpleapp.service.IOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class RedisOtpService implements IOtpService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final int OTP_VALID_DURATION = 5; // OTP valid duration in minutes

    public String generateOtp(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        Otp otpEntity = new Otp();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setCreatedAt(LocalDateTime.now());
        redisTemplate.opsForValue().set(email, otpEntity, OTP_VALID_DURATION, TimeUnit.MINUTES);
        return otp;
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        Otp otpEntity = (Otp) redisTemplate.opsForValue().get(email);
        if (otpEntity != null && otpEntity.getOtp().equals(otp)) {
            if (otpEntity.getCreatedAt().isAfter(LocalDateTime.now().minusMinutes(OTP_VALID_DURATION))) {
                return true;
            }
        }
        return false;
    }

}
