package com.ies.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="USER_DETAILS")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer caseNo;

	private String fullname;

	private String dob;

	private String ssn;

	private String gender;
	
	private String houseNum;

	private String city;

	private String state;

	private String email;
	
	private Long phone;
	
	private String planName;

	@Column(name = "CREADTED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createDate;
	
	@ManyToOne
	@JoinColumn(name="caseWorkerId")
	private CaseWorker caseWorker;
	
	@OneToOne
	@JoinColumn(name="edId")
	private EligDetailsEntity elig;
	
}