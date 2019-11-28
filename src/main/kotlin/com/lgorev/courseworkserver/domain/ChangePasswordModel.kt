package com.lgorev.courseworkserver.domain

data class ChangePasswordModel(
        val id: Long,
        val oldPassword: String,
        val newPassword: String
)