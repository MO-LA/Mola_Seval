package com.mo.domain.repository;

import com.mo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<User, Long> {
}
