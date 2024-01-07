package com.apertech.mailservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("hulya.garibli@apertech.net");
        message.setSubject("Test Email");
        message.setText("This is a test email sent from Spring Boot.");
        javaMailSender.send(message);
    }
}
