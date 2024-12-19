package com.ies.service;

import com.ies.binding.Entries;

import java.util.List;


public interface EdService {
	
	public String eligibilityCheck(Integer caseNo);
	
	public List<Entries> getAllData();

}
