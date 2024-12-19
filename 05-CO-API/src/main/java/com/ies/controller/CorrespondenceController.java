package com.ies.controller;

import javax.servlet.http.HttpServletResponse;

import com.ies.service.CorrespondenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CorrespondenceController {
	
	@Autowired
	private CorrespondenceService corrService;

	@GetMapping("/generatecorr/{caseNo}")
	public String generateCoor(HttpServletResponse response, @PathVariable Integer caseNo) throws Exception {
		
        response.setContentType("application/pdf");
		
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=Citizen.pdf";
		response.addHeader(headerKey, headerValue);
		corrService.exportPdf(response, caseNo);
		return "success";
	}

	@GetMapping("/notices")
	public String processNotices() {
		corrService.processPendingTriggers();
		return "Success";
	}
}
