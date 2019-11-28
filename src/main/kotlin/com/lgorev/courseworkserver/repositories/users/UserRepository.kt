package com.lgorev.courseworkserver.repositories.users

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: PagingAndSortingRepository<UserEntity, Long>, UserUpdateRepository {
    fun findByMail(mail: String): UserEntity?
    fun findByPhoneNumber(phoneNumber: String): UserEntity?
}