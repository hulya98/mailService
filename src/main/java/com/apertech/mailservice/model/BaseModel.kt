package com.apertech.mailservice.model

import java.util.*

open class BaseModel(

)

{
    var body: String? = null
    var header: String? = null
    var companyName: String? = null
    var language: String? = null
    val actualYear: Int = Calendar.getInstance().get(Calendar.YEAR)
    
    fun generateCopyRightText(): String? {
        return when (language) {
            "az" -> "©" + actualYear + " Sola ERP bütün hüquqlar qorunur."
            "en" -> "©" + actualYear + "  Sola ERP all rights reserved."
            else -> null
        }
    }

    fun generateAutomatedEmailText(): String? {
        return when (language) {
            "az" -> "Bu avtomatik yaradılan e-poçtdur - lütfən cavab verməyin."
            "en" -> "This is an automatically generated email - please do not reply"
            else -> null
        }
    }

    fun generateCompanyNameTeamText(): String? {
        return when (language) {
            "az" -> "$companyName Şirkəti"
            "en" -> "$companyName Team"
            else -> null
        }
    }

    fun generateDevelopedInformationText(): String? {
        return when (language) {
            "az" -> "Apertech Şirkəti tərəfindən hazırlanıb."
            "en" -> "Developed by Apertech Team"
            else -> null
        }
    }
}