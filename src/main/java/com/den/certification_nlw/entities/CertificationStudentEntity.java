package com.den.certification_nlw.entities;

import java.util.List;
import java.util.UUID;

public class CertificationStudentEntity {

	private UUID id;
	private UUID studentID;
	private String technology;
	private Integer grade;
	List<AnswersCertificationsEntity> answerCertificationsEntities;
		
	public CertificationStudentEntity() {	
	}
	
	public CertificationStudentEntity(UUID id, UUID studentID, String technology, Integer grade){
		this.id = id;
		this.studentID = studentID;
		this.technology = technology;
		this.grade = grade;	
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getStudentID() {
		return studentID;
	}

	public void setStudentID(UUID studentID) {
		this.studentID = studentID;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public List<AnswersCertificationsEntity> getAnswerCertificationsEntities() {
		return answerCertificationsEntities;
	}

	public void setAnswerCertificationsEntities(List<AnswersCertificationsEntity> answerCertificationsEntities) {
		this.answerCertificationsEntities = answerCertificationsEntities;
	}
}
