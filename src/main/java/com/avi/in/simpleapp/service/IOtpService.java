package com.avi.in.simpleapp.service;

public interface IOtpService {
    public String generateOtp(String email);
    public boolean verifyOtp(String email, String otp);
}
