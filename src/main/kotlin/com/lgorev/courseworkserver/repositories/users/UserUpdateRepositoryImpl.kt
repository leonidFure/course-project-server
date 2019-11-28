package com.lgorev.courseworkserver.repositories.users

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
@Transactional
class UserUpdateRepositoryImpl(@PersistenceContext val em: EntityManager) : UserUpdateRepository {

    override fun update(userEntity: UserEntity): UserEntity {
        val curUserEntity = em.find(UserEntity::class.java, userEntity.id)
        with(curUserEntity) {
            firstName = userEntity.firstName
            lastName = userEntity.lastName
            patronymic = userEntity.patronymic
            birthDate = userEntity.birthDate
            phoneNumber = userEntity.phoneNumber
            mail = userEntity.mail
            gender = userEntity.gender
        }
        return curUserEntity
    }

    override fun changePassword(id: Long, oldPassword: String, newPassword: String) {
        val curUserEntity = em.find(UserEntity::class.java, id)
        val query = "SELECT (e.password = crypt('$oldPassword', e.password)) FROM UserEntity e WHERE e.id = $id"
        val eqPassword = em.createQuery(query, Boolean::class.java).singleResult
        require(!eqPassword) {
            "Password incorrect"
        }
        curUserEntity.password = newPassword
    }
}