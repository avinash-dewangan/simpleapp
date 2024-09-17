package com.avi.in.simpleapp.common;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.Properties;


public class GmailService {

    @Autowired
    private static JavaMailSender javaMailSender;

    @Value("${spring.mail.host}")
    private static String host;

    @Value("${spring.mail.port}")
    private static String port;

    @Value("${spring.mail.username}")
    private static String username;

    @Value("${spring.mail.password}")
    private static String password;

    public static int sentMail(ArrayList<String> to, String subject, String body) {
        int res = 0;
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            // Set up properties for the mail session
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            // Create a session with an authenticator
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            for (int i = 0; i < to.size(); i++) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to.get(i)));
            }
            message.setSubject(subject);
            message.setText(body);
            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully!");
            res = 1;

        } catch (AddressException ae) {
            ae.printStackTrace();
            return 0;
        } catch (MessagingException me) {
            me.printStackTrace();
            return 0;
        }
        return res;
    }
}
//https://support.google.com/accounts/answer/185833?visit_id=638610326194756680-2369692407&p=InvalidSecondFactor&rd=1
// goto this link create new app for your password code