package com.thiagoaio.api.user

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp

import java.time.LocalDateTime

@Entity(name = 'users')
class UserModel {

    @Id
    @GeneratedValue
    UUID id

    @Column(nullable = false)
    String name

    @Column(nullable = false, unique = true)
    String email

    @Column(nullable = false)
    String password

    @CreationTimestamp
    LocalDateTime createdAt
}
