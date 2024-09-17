package com.avi.in.simpleapp.service.impl;

import com.avi.in.simpleapp.service.IEmailService;
import com.resend.Resend;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmailService implements IEmailService {

    @Value("${resend.api.key}")
    private String resendAPIKey;

    public String sentMail(String to, String Subject, String text){
        Resend resend = new Resend(resendAPIKey);

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("ecitizen <onboarding@resend.dev>")
                .to(to)
                //.attachments(att)
                .html("<strong>"+text+"</strong>")
                .subject(Subject)
                .build();

        SendEmailResponse data = resend.emails().send(sendEmailRequest);

        System.out.println(data);
        return data.getId();
    }

    @Override
    public String sentMail(ArrayList<String> to, String Subject, String text) {
        return null;
    }

    @Override
    public int mailTest() {
        return 0;
    }


}
