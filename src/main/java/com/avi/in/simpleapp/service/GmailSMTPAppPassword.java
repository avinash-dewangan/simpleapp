package com.avi.in.simpleapp.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailSMTPAppPassword {

    public static void main(String[] args) {

        // Gmail SMTP server settings
        String host = "smtp.gmail.com";
        String port = "587";
        String userEmail = "your-email@gmail.com"; // replace with your Gmail
        String password = "your-password"; // replace with your password or app-specific password

        // Recipient's email ID
        String toEmail = "recipient-email@example.com"; // replace with recipient's email

        // Email message settings
        String subject = "Test Email from Java";
        String body = "Hello, this is a test email sent from Java!";

        // Set up properties for the mail session
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a session with an authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userEmail, password);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
//https://support.google.com/accounts/answer/185833?visit_id=638610326194756680-2369692407&p=InvalidSecondFactor&rd=1
// goto this link create new app for your password code