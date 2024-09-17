package com.avi.in.simpleapp.dto;

import lombok.Data;

@Data
public class Email {
    private String toEmail;
    private String subject;
    private String body;

}
