package com.den.certification_nlw.controllers;

import com.den.certification_nlw.dto.AlternativesResultDTO;
import com.den.certification_nlw.dto.QuestionResultDTO;
import com.den.certification_nlw.entities.AlternativesEntity;
import com.den.certification_nlw.entities.QuestionEntity;
import com.den.certification_nlw.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        var result = this.questionRepository.findByTechnology(technology);

        var toMap = result.stream().map(question -> mapQuestionToDTO(question))
                .collect(Collectors.toList());
        return toMap;
    }



    static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
       var questionResultDTO = QuestionResultDTO.builder().id(question.getId())
               .technology(question.getTechnology())
               .description(question.getDescription()).build();

       List<AlternativesResultDTO> alternativesResultDTOs = question.getAlternatives()
               .stream().map(alternative -> mapAlternativeDTO(alternative))
               .collect(Collectors.toList());

       questionResultDTO.setAlternatives(alternativesResultDTOs);
       return questionResultDTO;
    }

    static AlternativesResultDTO mapAlternativeDTO(AlternativesEntity alternativesResultDTO){
       return AlternativesResultDTO.builder().id(alternativesResultDTO.getId()).description(alternativesResultDTO.getDescription()).build();
    }
}
