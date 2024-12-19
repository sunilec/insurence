package com.ies.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class KidsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer kidsId;
	private String kidName;
	private Long kidSSN;
	private Integer kidAge;
	
	private Integer caseNumber;
	
	@OneToOne
	@JoinColumn(name="caseNo")
	private User user;

}
