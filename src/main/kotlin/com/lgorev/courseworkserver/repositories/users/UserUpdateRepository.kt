package com.lgorev.courseworkserver.repositories.users

interface UserUpdateRepository {
    fun update(userEntity: UserEntity): UserEntity
    fun changePassword(id: Long, oldPassword: String, newPassword: String)
}