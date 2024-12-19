package com.ies.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Elig_Details_Table")
public class EligDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer edId;
	
	private String planName;
	
	private String planStatus;
	
	@Column(name = "ELIG_START_DATE")
	private LocalDate eligStartDate;

	@Column(name = "ELIG_END_DATE")
	private LocalDate eligEndDate;
	
	private double benefitAmt;
	
	private String denialReason;
	
	@OneToOne
	@JoinColumn(name="caseNo")
	private User user;
}