package com.den.certification_nlw.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "students")
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(unique = true, nullable = false)
	private String email;

	@OneToMany
	private List<CertificationStudentEntity> certificationStudentEntity;
}
