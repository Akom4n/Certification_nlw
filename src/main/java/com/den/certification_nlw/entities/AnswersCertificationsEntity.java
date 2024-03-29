package com.den.certification_nlw.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "answers_certification_students")
@Builder
public class AnswersCertificationsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "certification_id")
	private UUID certificationID;

	@ManyToOne()
	@JoinColumn(name = "certification_id", insertable = false, updatable = false)
	@JsonBackReference
	private CertificationStudentEntity certificationStudentEntity;

	@Column(name = "student_id")
	private UUID studentID;

	@ManyToOne
	@JoinColumn(name = "student_id", insertable = false, updatable = false)
	@JsonBackReference
	private StudentEntity studentEntity;

	@Column(name = "question_id")
	private UUID questionID;

	@Column(name = "answer_id")
	private UUID answerID;

	@Column(name = "is_correct")
	private Boolean isCorrect;

	@Timestamp
	private LocalDateTime createdAt;
}
