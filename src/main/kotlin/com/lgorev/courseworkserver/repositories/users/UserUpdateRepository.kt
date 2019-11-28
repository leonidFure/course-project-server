package com.lgorev.courseworkserver.repositories.users

interface UserUpdateRepository {
    fun changePassword(id: Long, oldPassword: String, newPassword: String)
}