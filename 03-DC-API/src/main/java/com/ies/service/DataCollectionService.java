package com.ies.service;

import com.ies.binding.DcSummary;
import com.ies.binding.Education;
import com.ies.binding.Income;
import com.ies.binding.PlanSelection;
import com.ies.requ.ChildReq;

import java.util.List;


public interface DataCollectionService {
	
	public List<String> getPlansName();
	
	public String saveSelectedPlan(PlanSelection select);
	
	public String saveIncomeData(Income income);
	
	public String saveEducationData(Education education);
	
	public String saveKidsData(ChildReq kids);
	
	public DcSummary getSummary(Integer caseNo);
	
}
