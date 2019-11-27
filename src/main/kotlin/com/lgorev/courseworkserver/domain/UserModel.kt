package com.lgorev.courseworkserver.domain

import java.time.LocalDate

data class UserModel(
        val id: Long = 0,
        val firstName: String = "",
        val lastName: String = "",
        val patronymic: String? = null,
        val birthDate: LocalDate = LocalDate.now(),
        val mail: String = "",
        val phoneNumber: String = "",
        val gender: String = "",
        val password: String = ""
)