package com.den.certification_nlw.repositories;

import com.den.certification_nlw.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository <QuestionEntity, UUID>{

    List<QuestionEntity> findByTechnology(String technology);
}
