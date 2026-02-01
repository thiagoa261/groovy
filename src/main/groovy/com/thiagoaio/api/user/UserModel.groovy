package com.thiagoaio.api.user

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp

import java.time.LocalDateTime

@Entity(name = 'users')
class UserModel {

    @Id
    @GeneratedValue
    @JsonIgnore
    UUID id

    @Column(nullable = false)
    String name

    @Column(nullable = false, unique = true)
    String email

    @Column(nullable = false)
    String password

    @Column(nullable = false)
    Boolean active = false

    @CreationTimestamp
    @JsonIgnore
    LocalDateTime createdAt
}
