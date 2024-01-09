package com.apertech.mailservice.model

class EmailTemplate(
    var emailTemplateId: Int,
    var subject: String,
    var header: String,
    var body: String? = null,
    var languageCode: String? = null,
    var emailNotificationId: Int? = null,
    var templateKey: String? = null
) {
}