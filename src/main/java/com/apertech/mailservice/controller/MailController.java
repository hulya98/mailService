package com.apertech.mailservice.controller;

import com.apertech.mailservice.service.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
public class MailController {

    private final  MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }


    @GetMapping
    public void sendMail() {
        mailService.sendEmail();
        System.out.println("Test");
    }
}
