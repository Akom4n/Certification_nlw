package com.den.certification_nlw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.den.certification_nlw.dto.VerifyHasCertificationDTO;
import com.den.certification_nlw.services.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	//Preciso usar o meu USECASE
	@Autowired
	private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

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
}
