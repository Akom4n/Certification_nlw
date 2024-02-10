package com.den.certification_nlw.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Builder
public class CertificationStudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(length = 100)
	private String technology;

	@Column(length = 10)
	private Integer grade;

	@Column(name = "student_id")
	private UUID studentID;

	@ManyToOne
	@JoinColumn(name = "student_id", insertable = false, updatable = false)
	private StudentEntity studentEntity;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "answer_certification_id", insertable = false, updatable = false)
	@JsonManagedReference
	List<AnswersCertificationsEntity> answerCertificationsEntities;

	@Timestamp
	private LocalDateTime createdAt;
}
