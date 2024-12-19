package com.ies.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


import lombok.Data;

@Data
@Entity
public class CorrespondenceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer noticeId;
	
	
	private String url;
	
	private LocalDate printDate;
	
	private String noticeStatus;

	private Long caseNumber;

	private String noticeUrl;
	
	
	private LocalDate createdDate;
	
	@OneToOne
	@JoinColumn(name="edId")
	private EligDetailsEntity eligFk;
	
	@OneToOne
	@JoinColumn(name="caseNo")
	private User user;
}
