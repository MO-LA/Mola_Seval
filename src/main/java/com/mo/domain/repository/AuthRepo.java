package com.mo.domain.repository;

import com.mo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepo extends JpaRepository<User, Long> {
    Optional<User> findUserById(String id);
}
