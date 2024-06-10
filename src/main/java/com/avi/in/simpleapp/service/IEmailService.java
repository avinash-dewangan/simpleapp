package com.avi.in.simpleapp.service;

public interface IEmailService {
    String sentMail(String to, String Subject, String text);
}
