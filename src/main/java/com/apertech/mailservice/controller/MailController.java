package com.apertech.mailservice.controller;

import com.apertech.mailservice.model.EmailTemplate;
import com.apertech.mailservice.model.EmailVerification;
import com.apertech.mailservice.service.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
public class MailController {

    private final MailService emailService;

    public MailController(MailService emailService) {
        this.emailService = emailService;
    }


    @PostMapping("/send-verification-email")
    public String sendVerificationEmail(String to, String subject, String body) {

        return "HTML email sent successfully!";
    }


}
