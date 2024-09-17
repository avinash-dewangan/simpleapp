package com.avi.in.simpleapp.service.impl;

import com.avi.in.simpleapp.common.ETemplate;
import com.avi.in.simpleapp.common.ProjectConstant;
import com.avi.in.simpleapp.service.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GmailService implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;




    public String sentMail(ArrayList<String> to, String subject, String body) {
        String res = "0";
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            // Pass 'true' to the helper to indicate the message is multipart
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            for (int i = 0; i < to.size(); i++) {
                helper.setTo(new InternetAddress(to.get(i)));
            }
            helper.setSubject(subject);
            String template = ProjectConstant.htmlContent;
            String mailBody = template.replaceAll("body", "http://localhost:8080"+"?userToken="+"123");

            helper.setText(mailBody, true); // 'true' indicates this is HTML content
            mailSender.send(mimeMessage);
            res = "1";
        } catch (AddressException ae) {
            ae.printStackTrace();
            return "0";
        } catch (MessagingException me) {
            me.printStackTrace();
            return "0";
        }
        return res;
    }

    @Override
    public int mailTest() {
        return ETemplate.accountActivationMail("titanicannu@gmail.com","avi","123");
    }

    @Override
    public String sentMail(String to, String Subject, String text) {
        return null;
    }
}
//https://support.google.com/accounts/answer/185833?visit_id=638610326194756680-2369692407&p=InvalidSecondFactor&rd=1
// goto this link create new app for your password code