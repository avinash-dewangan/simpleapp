package com.avi.in.simpleapp.controller;

import com.avi.in.simpleapp.dto.Email;
import com.avi.in.simpleapp.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class EmailController {


    @Autowired
    @Qualifier("gmailService")
    private IEmailService iEmailService;

    @PostMapping("/api/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody Email email) {
        String result = iEmailService.sentMail(email.getToEmail(), email.getSubject(), email.getBody());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/hello")
    public ResponseEntity<String> hello() {
        //String result = iEmailService.sentMail(email.getToEmail(), email.getSubject(), email.getBody());
       // ETemplate.accountActivationMail("titanicannu@gmail.com","avi","123");
       ArrayList list = new ArrayList<>();
       list.add("titanicannu@gmail.com");
       list.add("rajesh@qolarisdata.com");
       iEmailService.sentMail(list,"Test Mail","Hello");
       return ResponseEntity.ok("ok");
    }

}
