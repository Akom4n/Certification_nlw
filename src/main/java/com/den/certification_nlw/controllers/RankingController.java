package com.den.certification_nlw.controllers;

import com.den.certification_nlw.entities.CertificationStudentEntity;
import com.den.certification_nlw.repositories.CertificationStudentRepository;
import com.den.certification_nlw.useCases.Top10RankingUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private Top10RankingUseCase top10RankingUseCase;

    @GetMapping("/top10")
    public List<CertificationStudentEntity> top10(){
        return this.top10RankingUseCase.execute();
    }
}
