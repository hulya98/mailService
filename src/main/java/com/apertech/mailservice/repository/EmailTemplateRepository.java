package com.apertech.mailservice.repository;

import com.apertech.mailservice.model.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Integer> {
    Optional<EmailTemplate> findByTemplateKeyAndLanguageCode(String templateKey, String languageCode);
}
