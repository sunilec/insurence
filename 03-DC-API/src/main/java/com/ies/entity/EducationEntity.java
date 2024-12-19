package com.ies.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class EducationEntity {
	
	@Id
	@GeneratedValue
	private Integer educationId;
	private String highestDegree;
	private String graduationYear;
	private String universityName;
	private Integer caseNumber;
	
	@OneToOne
	@JoinColumn(name="caseNo")
	private User user;

}
