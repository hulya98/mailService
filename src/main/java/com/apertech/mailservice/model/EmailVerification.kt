package com.apertech.mailservice.model


class EmailVerification(

) : BaseModel() {

    var token: String? = null
    var subject: String? = null
    var userName: String? = null
    fun generateButtonText(): String? {
        return when (language) {
            "az" -> "E-poçtu indi təsdiq et"
            "en" -> "Verify Email Now"
            else -> null
        }!!
    }

    fun templateName(): String {
        return "email-verification"
    }

    fun getEmailVerifiedLink(): String {
        return "http://38.242.216.187:3030/sources/templates/EmailConfirmPage.html?verifyToken=$token"
    }
}