package com.den.certification_nlw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCertificationAnswerDTO {

    private String email;
    private String technology;
    private List<QuestionAnswerDTO> questionAnswer;
}
