package com.den.certification_nlw.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "certifications")
public class CertificationStudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(length = 100)
	private String technology;

	@Column(length = 10)
	private Integer grade;

	@JoinColumn(name = "student_id")
	private UUID studentID;

	// List<AnswersCertificationsEntity> answerCertificationsEntities;
}
