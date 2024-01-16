package com.apertech.mailservice.service;

import com.apertech.mailservice.model.BaseModel;
import com.apertech.mailservice.model.EmailRegistrationPending;
import com.apertech.mailservice.model.EmailTemplate;
import com.apertech.mailservice.model.EmailVerification;
import com.apertech.mailservice.repository.EmailTemplateRepository;
import jakarta.mail.internet.InternetAddress;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.text.MessageFormat;
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
        sendEmailWithHtmlTemplate(to, subject, "email-verification", context);
    }

    public void sendRegistrationPendingMail(String userName, String subject, String to) {
        Context context = fillRegistrationPendingContextData(userName, to);
        sendEmailWithHtmlTemplate(to, subject, "registration-pending", context);
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

    private Context setStaticDataToContext(String key) {
        var data = getBaseData(key, "az");
        Context context = new Context();
        context.setVariable("header", data.getHeader());
        context.setVariable("subject", data.getSubject());
        context.setVariable("body", data.getBody());
        return context;
    }

    private Context fillVerificationContextData(String userName, String token, String to) {
        EmailVerification verification = new EmailVerification();
        Context context = setStaticDataToContext("REG");
        context.setVariable("automatEmailText", verification.generateAutomatedEmailText());
        context.setVariable("buttonText", verification.generateButtonText());
        context.setVariable("link", verification.getEmailVerifiedLink());
        context.setVariable("companyName", verification.getCompanyName());
        context.setVariable("developedInfo", verification.generateDevelopedInformationText());
        context.setVariable("copyRight", verification.generateCopyRightText());
        return context;
    }

    private Context fillRegistrationPendingContextData(String userName, String to) {
        EmailRegistrationPending pending = new EmailRegistrationPending();
        Context context = setStaticDataToContext("RGA");
        context.setVariable("companyName", pending.getCompanyName());
        context.setVariable("developedInfo", pending.generateDevelopedInformationText());
        context.setVariable("copyRight", pending.generateCopyRightText());
        return context;
    }

    private BaseModel getBaseData(String key, String lang) {
        var templateData = getTemplateData(key, lang);
        BaseModel baseModel = new BaseModel();
        baseModel.setSubject(templateData.get().getSubject());
        baseModel.setHeader(templateData.get().getHeader());
        baseModel.setBody(templateData.get().getBody());
        baseModel.setLanguage(lang);
        return baseModel;
    }

    private Optional<EmailTemplate> getTemplateData(String templateKey, String languageCode) {
        var data = mailRepository.findByTemplateKeyAndLanguageCode(templateKey, languageCode);
        return data;
    }

}
