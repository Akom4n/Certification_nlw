package com.den.certification_nlw.services;

import com.den.certification_nlw.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.den.certification_nlw.dto.VerifyHasCertificationDTO;

@Service
public class VerifyIfHasCertificationUseCase {

	@Autowired
	private CertificationStudentRepository certificationStudentRepository;


	public boolean execute(VerifyHasCertificationDTO dto) {
		var result = this.certificationStudentRepository.findByStudentEmailAndTechnology(dto.getEmail(),
				dto.getTechnology());
		if(!result.isEmpty()) {
			return true;
		}
		return false;
	}
}
