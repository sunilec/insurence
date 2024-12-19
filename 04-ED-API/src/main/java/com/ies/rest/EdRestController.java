package com.ies.rest;

import java.util.List;

import com.ies.binding.Entries;
import com.ies.service.EdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EdRestController {

	@Autowired
	private EdService service;

	@GetMapping("/checkEligibility/{caseNo}")
	public ResponseEntity<String> eligibilityCheck(@PathVariable Integer caseNo) {
		String elig = service.eligibilityCheck(caseNo);
		return new ResponseEntity<>(elig, HttpStatus.OK);
	}

	@GetMapping("/getCitizensData")
	public ResponseEntity<List<Entries>> loadData() {
		 List<Entries> allData = service.getAllData();
		return new ResponseEntity<>(allData, HttpStatus.OK);
	}
}