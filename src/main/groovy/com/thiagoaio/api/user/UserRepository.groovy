package com.thiagoaio.api.user

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByEmail(String email);
}
