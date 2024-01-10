package com.apertech.mailservice.service;

import com.apertech.mailservice.model.EmailTemplate;
import com.apertech.mailservice.model.EmailVerification;
import com.apertech.mailservice.repository.EmailTemplateRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Optional;


@Service
public class MailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final EmailTemplateRepository mailRepository;

    public MailService(JavaMailSender javaMailSender, TemplateEngine templateEngine, EmailTemplateRepository mailRepository) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.mailRepository = mailRepository;
    }

    public void sendVerificationMail(String userName, String subject, String token, String to) {
        Context context = fillVerificationContextData(userName, token, to);
        sendEmailWithHtmlTemplate(to, subject, "email-template", context);
    }

    private void sendEmailWithHtmlTemplate(String to, String subject, String templateName, Context context) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            helper.setTo(to);
            helper.setSubject(subject);
            String htmlContent = templateEngine.process(templateName, context);
            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Handle exception
        }
    }


    private Context fillVerificationContextData(String userName, String token, String to) {
        var verificationData = emailVerification();
        Context context = new Context();
        context.setVariable("header", verificationData.getHeader());
        context.setVariable("automatEmailText", verificationData.generateAutomatedEmailText());
        context.setVariable("body", verificationData.getBody());
        context.setVariable("buttonText", verificationData.generateButtonText());
        context.setVariable("link", verificationData.getEmailVerifiedLink());
        context.setVariable("companyName", verificationData.getCompanyName());
        context.setVariable("developedInfo", verificationData.generateDevelopedInformationText());
        context.setVariable("copyRight", verificationData.generateCopyRightText());
        return context;
    }

    private EmailVerification emailVerification() {
        var templateData = getTemplateData("VER", "az");
        EmailVerification verification = new EmailVerification();
        verification.setSubject(templateData.get().getSubject());
        verification.setHeader(templateData.get().getHeader());
        verification.setBody(templateData.get().getBody());
        return verification;
    }

    private Optional<EmailTemplate> getTemplateData(String templateKey, String languageCode) {
        var data = mailRepository.findByTemplateKeyAndLanguageCode(templateKey, languageCode);
        return data;
    }

}
