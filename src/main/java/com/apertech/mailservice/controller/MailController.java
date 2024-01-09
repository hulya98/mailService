package com.apertech.mailservice.controller;

import com.apertech.mailservice.model.EmailVerification;
import com.apertech.mailservice.service.MailService;
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


    @PostMapping("/send-email")
    public String sendEmail(String to, String subject, String body) {
        emailService.sendEmail(to, subject, body);
        return "Email sent successfully!";
    }

    @PostMapping("/send-verification-email")
    public String sendVerificationEmail(String to, String subject, String body) {


//        EmailVerificationModel model = new EmailVerificationModel();
//        model.language = "az";
//        model.companyName = "Apertech";
//        model.header = "Email Verification";
//        Context context = new Context();
////        context.setVariable("body", "neter olcihdi");
//        context.setVariable("header", model.header);
//        context.setVariable("buttonText", model.generateButtonText());
////        context.setVariable("link", model.getEmailVerifiedLink());
//        context.setVariable("companyName", model.GenerateCompanyNameTeamText());
//        context.setVariable("developedInfo", model.GenerateDevelopedInformationText());
//        context.setVariable("copyRight", model.GenerateCopyRightText());
//
//        emailService.sendEmailWithHtmlTemplate(to, subject, "email-template", context);

        return "HTML email sent successfully!";
    }
}
