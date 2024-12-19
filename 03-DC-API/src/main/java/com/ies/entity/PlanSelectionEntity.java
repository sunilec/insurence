package com.ies.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class PlanSelectionEntity {
	
	@Id
	@GeneratedValue
	private Integer selectionId;
	private String planName;
	
	@OneToOne
	@JoinColumn(name="caseNo")
	private User caseNo;
}