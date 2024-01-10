package com.apertech.mailservice.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class EmailTemplate(
    @Id
    var emailTemplateId: Int? = null,
    var subject: String? = null,
    var header: String? = null,
    var body: String? = null,
    var languageCode: String? = null,
    var emailNotificationId: Int? = null,
    var templateKey: String? = null
) {
}