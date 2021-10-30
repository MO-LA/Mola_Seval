package com.mo.domain.repository;

import com.mo.domain.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepo extends JpaRepository<School, Long> {
    Optional<School> findBySchoolCode(String schoolCode);
}
