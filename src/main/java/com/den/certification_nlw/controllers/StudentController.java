package com.den.certification_nlw.controllers;

import com.den.certification_nlw.dto.StudentCertificationAnswerDTO;
import com.den.certification_nlw.dto.VerifyHasCertificationDTO;
import com.den.certification_nlw.entities.CertificationStudentEntity;
import com.den.certification_nlw.services.VerifyIfHasCertificationUseCase;
import com.den.certification_nlw.useCases.StudentCertificationAnswersUseCase;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	//Preciso usar o meu USECASE
	@Autowired
	private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

	@Autowired
	private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

	@PostMapping("/verifyIfHasCertification")
	public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
		
		// Email 
		// Technology
		var result = this.verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO);
		if(result) {
			return "Usuário já fez a prova";
		}
		
		return "Usuário pode fazer a prova";
	}

	@PostMapping("/certification/answer")
	public ResponseEntity<Object> certificationAnswer(
			@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO){
		try{
			var result = studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
			return ResponseEntity.ok().body(result);
		}catch (Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
}
