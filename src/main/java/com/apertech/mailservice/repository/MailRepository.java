package com.apertech.mailservice.repository;

import com.apertech.mailservice.model.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<EmailTemplate, Integer> {
    EmailTemplate findAllByTemplateKeyAndLanguageCode(String templateKey, String languageCode);
}
