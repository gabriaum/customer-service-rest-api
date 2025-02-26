package com.gabriaum.service.repository;

import com.gabriaum.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findById(long id);

    User findBySecretId(UUID secretId);
}
