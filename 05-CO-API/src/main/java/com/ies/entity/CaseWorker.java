package com.ies.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "CASE_WORKER")
public class CaseWorker {

	@Id
	@GeneratedValue
	@Column(name = "CASE_WORKER_ID")
	private Integer caseWorkerId;

	@Column(name = "CASE_WORKER_NAME")
	private String caseWorkerName;

	@Column(name = "CASE_WORKER_EMAIL")
	private String caseWorkerEmail;

	@Column(name = "CASE_WORKER_PWD")
	private String caseWorkerPwd;

	@Column(name = "CASE_WORKER_MOB")
	private Long caseWorkerMob;

	@Column(name = "CASE_WORKER_GENDER")
	private String caseWorkerGender;
	
	@Column(name = "CASE_WORKER_DOB")
	private LocalDate caseWorkerDOB;

	@Column(name = "CASE_WORKER_SSN")
	private Long caseWorkerSSN;
	
	@Column(name="ACTIVE_SW")
	private String activeSw="Y";
	
	@Column(name = "ACC_STATUS")
	private String accStatus="LOCKED";

	@Column(name = "CREADTED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createDate;
	
	@Column(name = "UPDATE_DATE", insertable = false)
	@UpdateTimestamp
	private LocalDate updateDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

 }
