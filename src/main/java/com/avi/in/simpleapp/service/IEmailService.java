package com.avi.in.simpleapp.service;

import java.util.ArrayList;

public interface IEmailService {
    String sentMail(String to, String Subject, String text);
    String sentMail(ArrayList<String> to, String Subject, String text);
    int mailTest();
}
