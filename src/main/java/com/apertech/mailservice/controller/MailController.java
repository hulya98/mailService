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

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }


    @PostMapping("/send-verification-email")
    public String sendVerificationEmail(String userName, String subject, String to, String token) {
        mailService.sendVerificationMail(userName, subject, token, to);
        return "HTML email sent successfully!";
    }

    @PostMapping("/send-registration-pending")
    public String sendRegistrationPending(String userName, String subject, String to) {
        mailService.sendRegistrationPendingMail(userName, subject, to);
        return "HTML email send successfully!";
    }

}
