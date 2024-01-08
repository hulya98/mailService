package com.apertech.mailservice.controller;

import com.apertech.mailservice.model.EmailRequest;
import com.apertech.mailservice.service.MailService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

@RestController
@RequestMapping("/api/v1/mail")
public class MailController {

    private final MailService emailService;

    public MailController(MailService emailService) {
        this.emailService = emailService;
    }


    @PostMapping("/send-email")
    public String sendEmail(String to, String subject, String body) {
        emailService.sendEmail(to, subject, body);
        return "Email sent successfully!";
    }

    @PostMapping("/send-html-email")
    public String sendHtmlEmail(String to, String subject, String body) {
        Context context = new Context();
        context.setVariable("message", body);

        emailService.sendEmailWithHtmlTemplate(to, subject, "email-template", context);
        return "HTML email sent successfully!";
    }
}
