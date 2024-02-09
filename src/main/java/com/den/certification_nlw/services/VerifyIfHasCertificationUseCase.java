package com.den.certification_nlw.services;

import org.springframework.stereotype.Service;

import com.den.certification_nlw.dto.VerifyHasCertificationDTO;

@Service
public class VerifyIfHasCertificationUseCase {

	public boolean execute(VerifyHasCertificationDTO dto) {
		if(dto.getEmail().equals("vinicius@gmail.com") && dto.getTechnology().equals("JAVA")) {
			return true;
		}
		return false;
	}
}
