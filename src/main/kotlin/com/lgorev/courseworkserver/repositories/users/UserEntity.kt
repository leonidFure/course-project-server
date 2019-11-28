package com.lgorev.courseworkserver.repositories.users

import org.hibernate.annotations.DynamicUpdate
import org.hibernate.validator.constraints.UniqueElements
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "usr",
        uniqueConstraints = [
            UniqueConstraint(columnNames = ["email"]),
            UniqueConstraint(columnNames = ["phone_number"])
        ])
@DynamicUpdate
data class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "_id")
        var id: Long = 0,
        @Column(name = "first_name", length = 20, nullable = false)
        var firstName: String = "",
        @Column(name = "last_name", length = 20, nullable = false)
        var lastName: String = "",
        @Column(name = "patronymic", length = 20)
        var patronymic: String? = null,
        @Column(name = "birth_date", nullable = false)
        var birthDate: LocalDate = LocalDate.now(),
        @Column(name = "email", unique = true, nullable = false)
        var mail: String = "",
        @Column(name = "phone_number", length = 15, nullable = false)
        var phoneNumber: String = "",
        @Column(name = "gender", length = 15, nullable = false)
        var gender: String = "",
        @Column(name = "_password", length = 100, nullable = false)
        var password: String
)