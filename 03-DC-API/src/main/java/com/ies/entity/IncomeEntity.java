package com.ies.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class IncomeEntity {
	
	@Id
	@GeneratedValue
	private Integer incomeId;
	private Double monthlySalaryIncome;
	private Double propertyIncome;
	private Double rentIncome;
	private Integer caseNumber;
	
	@OneToOne
	@JoinColumn(name="caseNo")
	private User user;

}
