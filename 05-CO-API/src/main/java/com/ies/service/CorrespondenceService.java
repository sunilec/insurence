package com.ies.service;



import javax.servlet.http.HttpServletResponse;

public interface CorrespondenceService {
	
	public String generateNotice(Integer caseNo);
	
	public boolean exportPdf(HttpServletResponse response, Integer caseNo) throws Exception;

	public void processPendingTriggers();


}
