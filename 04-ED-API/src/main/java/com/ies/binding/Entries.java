package com.ies.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Entries {

	private Integer edId;

	private String planName;

	private String planStatus;

	private LocalDate eligStartDate;

	private LocalDate eligEndDate;

	private double benefitAmt;

	private String denialReason;

}
