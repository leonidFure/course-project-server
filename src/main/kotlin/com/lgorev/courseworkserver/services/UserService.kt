package com.lgorev.courseworkserver.services

import com.lgorev.courseworkserver.domain.ChangePasswordModel
import com.lgorev.courseworkserver.domain.UserModel
import com.lgorev.courseworkserver.exceptions.NotFoundException
import com.lgorev.courseworkserver.exceptions.UniqueConstrainException
import com.lgorev.courseworkserver.repositories.users.UserEntity
import com.lgorev.courseworkserver.repositories.users.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [UniqueConstrainException::class, NotFoundException::class])
class UserService(private val userRepository: UserRepository) {

    private val log = LoggerFactory.getLogger(UserService::class.java)

    fun findAll(): MutableList<UserModel> {
        return userRepository.findAll().map { it.toModel() }.toMutableList()
    }

    fun save(userModel: UserModel): UserEntity {
        log.info("Try to save user")
        if (userRepository.findByMail(userModel.mail) != null) {
            log.warn("User with mail: ${userModel.mail} already exists")
            throw UniqueConstrainException("User with mail: ${userModel.mail} already exists")
        }

        if (userRepository.findByPhoneNumber(userModel.phoneNumber) != null) {
            log.warn("User with phone number: ${userModel.phoneNumber} already exists")
            throw UniqueConstrainException("User with phone number: ${userModel.phoneNumber} already exists")
        }
        log.info("User successfully saved")
        return userRepository.save(userModel.toEntity())
    }

    fun update(userModel: UserModel): UserEntity {
        log.info("Try to update user")
        val findByMail = userRepository.findByMail(userModel.mail)
        if (findByMail != null && findByMail.id != userModel.id) {
            log.warn("User with mail: ${userModel.mail} already exists")
            throw UniqueConstrainException("User with mail: ${userModel.mail} already exists")
        }

        val findByPhoneNumber = userRepository.findByPhoneNumber(userModel.phoneNumber)
        if (findByPhoneNumber != null && findByPhoneNumber.id != userModel.id) {
            log.warn("User with phone number: ${userModel.phoneNumber} already exists")
            throw UniqueConstrainException("User with phone number: ${userModel.phoneNumber} already exists")
        }
        log.info("User successfully updated")
        return userRepository.update(userModel.toEntity())
    }

    fun delete(id: Long) {
        return try {
            userRepository.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw NotFoundException("User with id: $id not found")
        }
    }

    fun findOne(id: Long): UserEntity {
        return userRepository.findById(id).orElseThrow {
            NotFoundException("User with id: $id not found")
        }
    }

    fun findPage(pageable: Pageable): Page<UserEntity> {
        return userRepository.findAll(pageable)
    }

    fun changePassword(model: ChangePasswordModel) {
        return userRepository.changePassword(model.id, model.oldPassword, model.newPassword)
    }



    fun UserEntity.toModel(): UserModel {
        return UserModel(id, firstName, lastName, patronymic, birthDate, mail, phoneNumber, gender, password)
    }

    fun UserModel.toEntity(): UserEntity {
        return UserEntity(id, firstName, lastName, patronymic, birthDate, mail, phoneNumber, gender, password)
    }
}