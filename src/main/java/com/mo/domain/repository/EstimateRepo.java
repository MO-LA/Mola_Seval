package com.mo.domain.repository;

import com.mo.domain.entity.Estimate;
import com.mo.domain.entity.School;
import com.mo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstimateRepo extends JpaRepository<Estimate, Long> {
    Optional<Estimate> findBySchoolAndUser(School school, User user);
}
