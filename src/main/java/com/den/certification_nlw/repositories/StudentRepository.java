package com.den.certification_nlw.repositories;

import com.den.certification_nlw.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository <StudentEntity, UUID> {

    public Optional<StudentEntity> findByEmail(String email);
}
