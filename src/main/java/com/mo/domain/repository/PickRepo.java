package com.mo.domain.repository;

import com.mo.domain.entity.Pick;
import com.mo.domain.entity.School;
import com.mo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PickRepo extends JpaRepository<Pick, Long> {
    Optional<Pick> findBySchoolAndUser(School school, User user);

}
