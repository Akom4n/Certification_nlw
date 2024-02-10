package com.den.certification_nlw.entities;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;

import java.time.LocalDateTime;
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

	@ManyToOne
	@JoinColumn(name = "student_id", insertable = false, updatable = false)
	private StudentEntity studentEntity;

	@OneToMany
	@JoinColumn(name = "answer_certification_id")
	List<AnswersCertificationsEntity> answerCertificationsEntities;

	@Timestamp
	private LocalDateTime createdAt;
}
