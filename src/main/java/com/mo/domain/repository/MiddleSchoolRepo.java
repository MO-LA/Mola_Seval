package com.mo.domain.repository;

import com.mo.domain.entity.MiddleSchool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MiddleSchoolRepo extends JpaRepository<MiddleSchool, Long> {
    Optional<List<MiddleSchool>> findAllByNameContaining(String name);
}
