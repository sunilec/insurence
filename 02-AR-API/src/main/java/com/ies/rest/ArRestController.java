package com.ies.rest;

import java.util.List;

import com.ies.bindings.App;
import com.ies.service.ArService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ArRestController {

	@Autowired
	private ArService arService;

	// http://localhost:9092/app
	@PostMapping("/app")
	public ResponseEntity<String> createApp(@RequestBody App app){
		
		String status = arService.createApplication(app);
		
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@GetMapping("/apps/{userId}")
	public List<App> getApps(@PathVariable Integer userId){
		return arService.fetchApps(userId);
	}
	
}
