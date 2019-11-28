package com.lgorev.courseworkserver.repositories.users

import com.lgorev.courseworkserver.exceptions.PasswordIncorrectException
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

typealias bool = java.lang.Boolean

@Repository
@Transactional
class UserUpdateRepositoryImpl(@PersistenceContext val em: EntityManager) : UserUpdateRepository {

    override fun changePassword(id: Long, oldPassword: String, newPassword: String) {
        val curUserEntity = em.find(UserEntity::class.java, id)
        val query = "SELECT (e.password = crypt('$oldPassword', e.password)) FROM UserEntity e WHERE e.id = $id"
        val eqPassword = em.createQuery(query, bool::class.java).singleResult.booleanValue()
        if(!eqPassword) {
            throw PasswordIncorrectException("Password incorrect")
        }
        curUserEntity.password = newPassword
    }
}