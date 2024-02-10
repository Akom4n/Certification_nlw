package com.den.certification_nlw.useCases;

import com.den.certification_nlw.dto.StudentCertificationAnswerDTO;
import com.den.certification_nlw.dto.VerifyHasCertificationDTO;
import com.den.certification_nlw.entities.AnswersCertificationsEntity;
import com.den.certification_nlw.entities.CertificationStudentEntity;
import com.den.certification_nlw.entities.QuestionEntity;
import com.den.certification_nlw.entities.StudentEntity;
import com.den.certification_nlw.repositories.CertificationStudentRepository;
import com.den.certification_nlw.repositories.QuestionRepository;
import com.den.certification_nlw.repositories.StudentRepository;
import com.den.certification_nlw.services.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception{

        var hasCertification = this.verifyIfHasCertificationUseCase.execute(new VerifyHasCertificationDTO(dto.getEmail(), dto.getTechnology()));

        if(hasCertification) {
            throw new Exception("Você já tirou sua certificação!");
        }

        // Buscar as alternativas das perguntas
            // - Correct or Incorrect

        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionAnswer()
                .stream().forEach(questionAnswer -> {
                   var question = questionsEntity.stream()
                           .filter(q -> q.getId().equals(questionAnswer.getQuestionID()))
                           .findFirst().get();

                   var findCorrectAlternative = question.getAlternatives().stream()
                           .filter(alternative -> alternative.isCorrect()).findFirst().get();

                   if(findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())){
                       questionAnswer.setCorrect(true);
                       correctAnswers.incrementAndGet();
                   }
                   else {
                       questionAnswer.setCorrect(false);
                   }

                   var answerrsCertificationsEntity = AnswersCertificationsEntity.builder()
                           .answerID(questionAnswer.getAlternativeID())
                           .questionID(questionAnswer.getQuestionID())
                           .isCorrect(questionAnswer.isCorrect()).build();

                   answersCertifications.add(answerrsCertificationsEntity);
                });

        //Verificar se existe student pelo email
        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentID;
        if(student.isEmpty()){
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        }
        else {
            studentID = student.get().getId();
        }

        CertificationStudentEntity certificationStudentEntity =
                CertificationStudentEntity.builder()
                        .technology(dto.getTechnology())
                        .studentID(studentID)
                        .grade(correctAnswers.get())
                        .build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answerCertification -> {
            answerCertification.setCertificationID(certificationStudentEntity.getId());
            answerCertification.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAnswerCertificationsEntities(answersCertifications);
        certificationStudentRepository.save(certificationStudentEntity);

        return certificationStudentCreated;
        //Salvar as informações da certificação
    }
}
