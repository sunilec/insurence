package com.ies.rest;

import java.util.List;

import com.ies.binding.DcSummary;
import com.ies.binding.Education;
import com.ies.binding.Income;
import com.ies.binding.PlanSelection;
import com.ies.requ.ChildReq;
import com.ies.service.DataCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DcRestController {

	@Autowired
	private DataCollectionService service;

	@GetMapping("/getPlanName")
	public ResponseEntity<List<String>> getPlanNames() {
		List<String> plansName = service.getPlansName();
		return new ResponseEntity<>(plansName, HttpStatus.OK);
	}

	@PostMapping("/planInsertion")
	public ResponseEntity<String> PlanSelection(@RequestBody PlanSelection selectionPlan) {
		String saveSelectedPlan = service.saveSelectedPlan(selectionPlan);
		return new ResponseEntity<>(saveSelectedPlan, HttpStatus.CREATED);
	}

	@PostMapping("/saveIncome")
	public String saveCitizenIncome(@RequestBody Income binding) {

		return service.saveIncomeData(binding);
	}

	@PostMapping("/saveEdu")
	public String saveEduDetails(@RequestBody Education edu) {
		return service.saveEducationData(edu);
	}

	@PostMapping("/saveChilds")
	public String saveKids(@RequestBody ChildReq child) {
		return service.saveKidsData(child);
	}

	@GetMapping("/getAllData/{caseNo}")
	public ResponseEntity<DcSummary> getCitizenDetails(@PathVariable Integer caseNo) {
		DcSummary summary = service.getSummary(caseNo);
		return new ResponseEntity<>(summary, HttpStatus.OK);
	}
}
